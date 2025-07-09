// EmployeeDTO.java
package com.example.lpgmanagement.dto.employee;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class EmployeeDTO {
    private Long id;
    private String name;
    private String employeeCode;
    private String designation;
    private Set<String> roles;
    private Long aadharNumber;
    private Long mobile;
    private String careof;
    private String village;
    private Long wardNo;
    private String panchayat;
    private String district;
    private Boolean isActive;
}

