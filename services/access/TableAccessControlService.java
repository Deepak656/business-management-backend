package com.example.lpgmanagement.services.access;

import com.example.lpgmanagement.dto.access.*;
import com.example.lpgmanagement.exception.ResourceNotFoundException;
import com.example.lpgmanagement.models.access.TableAccessControl;
import com.example.lpgmanagement.models.access.TableRolePermission;
import com.example.lpgmanagement.repositories.access.TableAccessControlRepository;
import com.example.lpgmanagement.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class TableAccessControlService {

    private final TableAccessControlRepository tableAccessRepository;

    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null ? auth.getName() : null;
    }

    public String getCreatedById() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof UserPrincipal) {
            return ((UserPrincipal) auth.getPrincipal()).getId().toString();
        }
        return null;
    }

    public String getLastModifiedById() {
        return getCreatedById();
    }
    @Transactional(readOnly = true)
    public String getCreatedBy() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null ? auth.getName() : null;
    }

    @Transactional(readOnly = true)
    public String getLastModifiedBy() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null ? auth.getName() : null;
    }
    public TableAccessControlResponse createTableAccess(TableAccessControlRequest request) {
        log.info("Creating table access control for table: {}", request.getTableName());

        TableAccessControl tableAccess = new TableAccessControl();
        tableAccess.setTableName(request.getTableName());
        tableAccess.setOpen(request.isOpen());
        tableAccess.setRolePermissions(mapRolePermissions(request.getRolePermissions()));

        tableAccess = tableAccessRepository.save(tableAccess);
        return mapToResponse(tableAccess);
    }

    public TableAccessControlResponse updateTableAccess(String tableName, TableAccessControlRequest request) {
        log.info("Updating table access control for table: {}", tableName);

        TableAccessControl tableAccess = tableAccessRepository.findByTableName(tableName)
                .orElseThrow(() -> new ResourceNotFoundException("Table access control not found for: " + tableName));

        tableAccess.setOpen(request.isOpen());
        tableAccess.setRolePermissions(mapRolePermissions(request.getRolePermissions()));

        tableAccess = tableAccessRepository.save(tableAccess);
        return mapToResponse(tableAccess);
    }

    public void toggleTableAccess(String tableName, boolean isOpen) {
        log.info("Toggling table access for table: {} to {}", tableName, isOpen);
        tableAccessRepository.updateTableAccessStatus(tableName, isOpen);
    }

    @Transactional(readOnly = true)
    public boolean isTableOpen(String tableName) {
        return tableAccessRepository.findByTableName(tableName)
                .map(TableAccessControl::isOpen)
                .orElse(false);
    }

    @Transactional(readOnly = true)
    public boolean hasPermission(String tableName, String role, String permission) {
        boolean requireView = "VIEW".equalsIgnoreCase(permission);
        boolean requireAdd = "ADD".equalsIgnoreCase(permission);
        boolean requireEdit = "EDIT".equalsIgnoreCase(permission);
        boolean requireDelete = "DELETE".equalsIgnoreCase(permission);

        return tableAccessRepository.hasPermission(tableName, role, requireView, requireAdd, requireEdit, requireDelete);
    }

    @Transactional(readOnly = true)
    public List<TableAccessControlResponse> getAllTableAccess() {
        return tableAccessRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private Set<TableRolePermission> mapRolePermissions(Set<TableRolePermissionRequest> requests) {
        return requests.stream()
                .map(req -> {
                    TableRolePermission permission = new TableRolePermission();
                    permission.setRole(req.getRole());
                    permission.setCanView(req.isCanView());
                    permission.setCanAdd(req.isCanAdd());
                    permission.setCanEdit(req.isCanEdit());
                    permission.setCanDelete(req.isCanDelete());
                    return permission;
                })
                .collect(Collectors.toSet());
    }

    private TableAccessControlResponse mapToResponse(TableAccessControl tableAccess) {
        TableAccessControlResponse response = new TableAccessControlResponse();
        response.setId(tableAccess.getId());
        response.setTableName(tableAccess.getTableName());
        response.setOpen(tableAccess.isOpen());
        response.setRolePermissions(mapToRolePermissionDTOs(tableAccess.getRolePermissions()));
        return response;
    }

    private Set<TableRolePermissionDTO> mapToRolePermissionDTOs(Set<TableRolePermission> permissions) {
        return permissions.stream()
                .map(perm -> {
                    TableRolePermissionDTO dto = new TableRolePermissionDTO();
                    dto.setRole(perm.getRole());
                    dto.setCanView(perm.isCanView());
                    dto.setCanAdd(perm.isCanAdd());
                    dto.setCanEdit(perm.isCanEdit());
                    dto.setCanDelete(perm.isCanDelete());
                    return dto;
                })
                .collect(Collectors.toSet());
    }
}