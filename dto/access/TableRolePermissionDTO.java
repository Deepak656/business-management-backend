package com.example.lpgmanagement.dto.access;

import lombok.Data;

@Data
public class TableRolePermissionDTO {
    private String role;
    private boolean canView;
    private boolean canAdd;
    private boolean canEdit;
    private boolean canDelete;
}
