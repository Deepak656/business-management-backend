package com.example.lpgmanagement.dto.employee;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class EmployeeResponse {
    private Long id;
    private String name;
    private String email;
    private String careof;
    private String employeeCode;
    private String designation;
    private Set<String> roles;
    private Boolean isActive;
}
