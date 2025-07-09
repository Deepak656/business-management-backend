package com.example.lpgmanagement.models.employee;

import com.example.lpgmanagement.models.base.Person;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.*;
import javax.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employees")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@EqualsAndHashCode(callSuper = true)
public class Employee extends Person {

    @NotBlank(message = "Designation is required")
    @Column(nullable = false)
    private String designation;

    private String email;
    private String username;


    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "employee_roles", joinColumns = @JoinColumn(name = "employee_id"))
    @Column(name = "roles", nullable = false)
    private Set<String> roles = new HashSet<>();

    @Column(name = "aadhar_number", columnDefinition = "bigint")
    private Long aadharNumber;

    @NotBlank(message = "Employee code is required")
    @Column(nullable = false, unique = true)
    private String employeeCode;

    @Column(name = "is_Active")
    private Boolean isActive = true;

    @PrePersist
    @PreUpdate
    private void validateRoles() {
        Set<String> validRoles = Set.of(
                "ROLE_Admin", "ROLE_Manager", "ROLE_GodownKeeper", "ROLE_KYCStaff",
                "ROLE_Mechanic", "ROLE_Owner", "ROLE_DeliveryPerson", "ROLE_CustomerCare"
        );
        for (String role : roles) {
            if (!validRoles.contains(role)) {
                throw new IllegalArgumentException("Invalid role: " + role);
            }
        }
    }
}