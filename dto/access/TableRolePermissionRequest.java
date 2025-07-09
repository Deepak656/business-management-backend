package com.example.lpgmanagement.dto.access;

import lombok.Data;

//import jakarta.validation.constraints.NotBlank;
import javax.validation.constraints.NotBlank;

@Data
public class TableRolePermissionRequest {
    @NotBlank(message = "Role is required")
    private String role;
    private boolean canView;
    private boolean canAdd;
    private boolean canEdit;
    private boolean canDelete;
}
