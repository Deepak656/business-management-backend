package com.example.lpgmanagement.models.access;

import com.example.lpgmanagement.models.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "table_access_control")
@Data
@EqualsAndHashCode(callSuper = true)
public class TableAccessControl extends BaseEntity {

    @Column(name = "table_name", nullable = false, unique = true)
    private String tableName;

    @Column(name = "is_open")
    private boolean isOpen = false;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "table_role_permissions",
            joinColumns = @JoinColumn(name = "table_access_id")
    )
    private Set<TableRolePermission> rolePermissions = new HashSet<>();
}

