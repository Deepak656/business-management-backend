package com.example.lpgmanagement.models.employee.types;

import com.example.lpgmanagement.models.employee.Employee;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "godown_keepers")
@Data
@EqualsAndHashCode(callSuper = true)
public class GodownKeeper extends Employee {

    @Column(name = "godown_code")
    private String godownCode;

    @Column(name = "storage_capacity")
    private Integer storageCapacity;

    @PrePersist
    protected void onCreate() {
        // Use a valid Set of roles
        Set<String> validRoles = Set.of(
                "ROLE_Admin", "ROLE_Manager", "ROLE_GodownKeeper", "ROLE_KYCStaff",
                "ROLE_Mechanic", "ROLE_Owner", "ROLE_DeliveryPerson", "ROLE_CustomerCare"
        );

        // Set roles and designation
        setRoles(Set.of("ROLE_GodownKeeper")); // Add only the allowed role
        setDesignation("Godown Keeper");
    }
}
