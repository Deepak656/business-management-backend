package com.example.lpgmanagement.controllers.access;

import com.example.lpgmanagement.dto.access.*;
import com.example.lpgmanagement.services.access.TableAccessControlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/access-control")
@RequiredArgsConstructor
public class TableAccessControlController {

    private final TableAccessControlService tableAccessService;

    @PostMapping("/tables")
    @PreAuthorize("hasRole('ROLE_Admin')")
    public ResponseEntity<TableAccessControlResponse> createTableAccess(
            @Valid @RequestBody TableAccessControlRequest request) {
        return ResponseEntity.ok(tableAccessService.createTableAccess(request));
    }

    @PutMapping("/tables/{tableName}")
    @PreAuthorize("hasRole('ROLE_Admin')")
    public ResponseEntity<TableAccessControlResponse> updateTableAccess(
            @PathVariable String tableName,
            @Valid @RequestBody TableAccessControlRequest request) {
        return ResponseEntity.ok(tableAccessService.updateTableAccess(tableName, request));
    }

    @PutMapping("/tables/{tableName}/toggle")
    @PreAuthorize("hasRole('ROLE_Admin')")
    public ResponseEntity<Void> toggleTableAccess(
            @PathVariable String tableName,
            @RequestParam boolean isOpen) {
        tableAccessService.toggleTableAccess(tableName, isOpen);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/tables")
    @PreAuthorize("hasRole('ROLE_Admin')")
    public ResponseEntity<List<TableAccessControlResponse>> getAllTableAccess() {
        return ResponseEntity.ok(tableAccessService.getAllTableAccess());
    }

    @GetMapping("/tables/{tableName}/status")
    @PreAuthorize("hasAnyRole('ROLE_Admin', 'MANAGER', 'GODOWN_KEEPER', 'MECHANIC')")
    public ResponseEntity<Boolean> isTableOpen(@PathVariable String tableName) {
        return ResponseEntity.ok(tableAccessService.isTableOpen(tableName));
    }

    @GetMapping("/tables/{tableName}/check-permission")
    @PreAuthorize("hasAnyRole('ROLE_Admin', 'MANAGER', 'GODOWN_KEEPER', 'MECHANIC')")
    public ResponseEntity<Boolean> checkPermission(
            @PathVariable String tableName,
            @RequestParam String role,
            @RequestParam String permission) {
        return ResponseEntity.ok(tableAccessService.hasPermission(tableName, role, permission));
    }
}