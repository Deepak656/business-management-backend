package com.example.lpgmanagement.services.employee;

import com.example.lpgmanagement.dto.employee.EmployeeCreateRequest;
import com.example.lpgmanagement.dto.employee.EmployeeResponse;
import com.example.lpgmanagement.dto.employee.EmployeeUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import jakarta.transaction.Transactional;
import java.util.List;

public interface EmployeeService {
    EmployeeResponse updateEmployee(Long id, EmployeeUpdateRequest request);
    EmployeeResponse getEmployeeById(Long id);
//    Page<EmployeeResponse> getAllEmployees(Pageable pageable);
//    Page<EmployeeResponse> searchEmployees(String search, Pageable pageable);
//    void deactivateEmployee(Long id);
//    void reactivateEmployee(Long id);
//    List<EmployeeResponse> getActiveEmployeesByRole(String role);

    @Transactional
    EmployeeResponse createEmployee(EmployeeCreateRequest request);
}

