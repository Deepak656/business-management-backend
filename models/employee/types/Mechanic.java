package com.example.lpgmanagement.models.employee.types;

import com.example.lpgmanagement.models.employee.Employee;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(name = "mechanics")
@Data
@EqualsAndHashCode(callSuper = true)
public class Mechanic extends Employee {

    @Column(name = "license_number")
    private String licenseNumber;

    @Column(name = "specialization")
    private String specialization;

    @Column(name = "experience_years")
    private Integer experienceYears;

    @PrePersist
    protected void onCreate() {
        // Use a valid Set of roles
        Set<String> validRoles = Set.of(
                "ROLE_Admin", "ROLE_Manager", "ROLE_GodownKeeper", "ROLE_KYCStaff",
                "ROLE_Mechanic", "ROLE_Owner", "ROLE_DeliveryPerson", "ROLE_CustomerCare"
        );

        // Set roles and designation
        setRoles(Set.of("ROLE_Mechanic")); // Add only the allowed role
        setDesignation("Vendor cum Mechanic");
    }
}