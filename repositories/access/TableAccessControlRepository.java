package com.example.lpgmanagement.repositories.access;

import com.example.lpgmanagement.models.access.TableAccessControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TableAccessControlRepository extends JpaRepository<TableAccessControl, Long> {

    Optional<TableAccessControl> findByTableName(String tableName);

    @Modifying
    @Query("UPDATE TableAccessControl t SET t.isOpen = :isOpen WHERE t.tableName = :tableName")
    void updateTableAccessStatus(String tableName, boolean isOpen);

    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM TableAccessControl t " +
            "JOIN t.rolePermissions rp " +
            "WHERE t.tableName = :tableName " +
            "AND rp.role = :role " +
            "AND (" +
            "(:requireView = false OR rp.canView = true) AND " +
            "(:requireAdd = false OR rp.canAdd = true) AND " +
            "(:requireEdit = false OR rp.canEdit = true) AND " +
            "(:requireDelete = false OR rp.canDelete = true)" +
            ")")
    boolean hasPermission(String tableName, String role, boolean requireView, boolean requireAdd, boolean requireEdit, boolean requireDelete);
}