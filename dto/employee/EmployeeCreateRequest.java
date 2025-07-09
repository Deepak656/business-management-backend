package com.example.lpgmanagement.dto.employee;

import com.example.lpgmanagement.dto.base.BaseRequest;
import javax.validation.constraints.Email;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import java.util.Set;

@Data
@EqualsAndHashCode()
public class EmployeeCreateRequest  {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Employee code is required")
    private String employeeCode;

    @NotBlank(message = "Role is required")
    private Set<String> roles;
    private String designation;
    private String email;
    @NotBlank(message = "Aadhar number is required")
    private Long aadharNumber;

    @NotBlank(message = "Mobile is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits")
    private Long mobile;

    private String careof;
    @NotBlank(message = "Village is required")
    private String village;

    @NotBlank(message = "Ward number is required")
    private Long wardNo;

    @NotBlank(message = "Panchayat is required")
    private String panchayat;

    @NotBlank(message = "District is required")
    private String district;
    private Boolean isActive;
}
