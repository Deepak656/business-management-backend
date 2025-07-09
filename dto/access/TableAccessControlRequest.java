// TableAccessControlRequest.java
package com.example.lpgmanagement.dto.access;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class TableAccessControlRequest {
    @NotBlank(message = "Table name is required")
    private String tableName;
    private boolean isOpen;
    private Set<TableRolePermissionRequest> rolePermissions;
}

