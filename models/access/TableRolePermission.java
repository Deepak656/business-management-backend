package com.example.lpgmanagement.models.access;

import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
@Data
public class TableRolePermission {
    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "can_view")
    private boolean canView = false;

    @Column(name = "can_add")
    private boolean canAdd = false;

    @Column(name = "can_edit")
    private boolean canEdit = false;

    @Column(name = "can_delete")
    private boolean canDelete = false;
}
