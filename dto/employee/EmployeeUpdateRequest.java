package com.example.lpgmanagement.dto.employee;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import java.util.Set;

@Data
public class EmployeeUpdateRequest {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Care of is required")
    private String careof;

    private String village;
    private Long wardNo;
    private String panchayat;
    private String district;
    private String email;

    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits")
    private Long mobile;
    private Set<String> roles;
    private String employeeCode;
    private String designation;
    private Boolean isActive;
    private Long aadharNumber;
}