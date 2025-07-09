package com.example.lpgmanagement.dto.access;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

// TableAccessControlResponse.java
@Data
public class TableAccessControlResponse {
    private Long id;
    private String tableName;
    private boolean isOpen;
    private Set<TableRolePermissionDTO> rolePermissions;
}
