package com.example.lpgmanagement.controllers.employee;

import com.example.lpgmanagement.dto.employee.EmployeeCreateRequest;
import com.example.lpgmanagement.dto.employee.EmployeeResponse;
import com.example.lpgmanagement.services.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_Admin')")
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody EmployeeCreateRequest request) {
        ResponseEntity<EmployeeResponse> responses = ResponseEntity.ok(employeeService.createEmployee(request));
        return responses;
    }

//    @GetMapping
//    @PreAuthorize("hasAnyRole('ROLE_Admin', 'MANAGER')")
//    public ResponseEntity<Page<EmployeeResponse>> getAllEmployees(Pageable pageable) {
//        ResponseEntity<Page<EmployeeResponse>> responses = ResponseEntity.ok(employeeService.getAllEmployees(pageable));
//        return responses;
//    }

//    @GetMapping("/search")
//    @PreAuthorize("hasAnyRole('ROLE_Admin', 'MANAGER')")
//    public ResponseEntity<Page<EmployeeResponse>> searchEmployees(@RequestParam String query, Pageable pageable) {
//        ResponseEntity<Page<EmployeeResponse>> responses = ResponseEntity.ok(employeeService.searchEmployees(query, pageable));
//        return responses;
//    }
}
