package com.example.lpgmanagement.services.access;

import com.example.lpgmanagement.models.access.TableAccessControl;
import com.example.lpgmanagement.repositories.access.TableAccessControlRepository;
import com.example.lpgmanagement.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccessControlServiceImpl {
    private final TableAccessControlRepository tableAccessControlRepository;

    @Transactional
    public void setTableAccess(String tableName, boolean isOpen) {
        TableAccessControl tableAccess = tableAccessControlRepository.findByTableName(tableName)
                .orElseThrow(() -> new ResourceNotFoundException("Table access control not found"));
        tableAccess.setOpen(isOpen);
        tableAccessControlRepository.save(tableAccess);
    }

    @Transactional(readOnly = true)
    public boolean isTableAccessible(String tableName) {
        return tableAccessControlRepository.findByTableName(tableName)
                .map(TableAccessControl::isOpen)
                .orElse(false);
    }

    @Transactional(readOnly = true)
    public Map<String, Boolean> getAllTableAccess() {
        List<TableAccessControl> allAccess = tableAccessControlRepository.findAll();
        return allAccess.stream()
                .collect(Collectors.toMap(
                        TableAccessControl::getTableName,
                        TableAccessControl::isOpen
                ));
    }

    @Transactional
    public void setRolePermissions(String tableName, String role, Map<String, Boolean> permissions) {
        TableAccessControl tableAccess = tableAccessControlRepository.findByTableName(tableName)
                .orElseThrow(() -> new ResourceNotFoundException("Table access control not found"));
        // Update permissions logic here
        tableAccessControlRepository.save(tableAccess);
    }
}