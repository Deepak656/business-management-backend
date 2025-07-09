package com.example.lpgmanagement.services.employee;

import com.example.lpgmanagement.dto.employee.*;
import com.example.lpgmanagement.exception.BusinessException;
import com.example.lpgmanagement.exception.ResourceNotFoundException;
import com.example.lpgmanagement.models.employee.Employee;
import com.example.lpgmanagement.repositories.employee.EmployeeRepository;
import com.example.lpgmanagement.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    @Transactional
    public EmployeeResponse createEmployee(EmployeeCreateRequest request) {
        Set<String> validRoles = Set.of(
                "ROLE_Admin", "Manager", "GodownKeeper", "KYCStaff",
                "Mechanic", "Owner", "DeliveryPerson", "CustomerCare"
        );
        for (String role : request.getRoles()) {
            if (!validRoles.contains(role)) {
                throw new BusinessException("Invalid role: " + role);
            }
        }
        Employee employee = employeeMapper.toEntity(request);
        employee = employeeRepository.save(employee);
        return employeeMapper.toResponse(employee);
    }

    @Override
    @Transactional
    public EmployeeResponse updateEmployee(Long id, EmployeeUpdateRequest request) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        Set<String> validRoles = Set.of(
                "ROLE_Admin", "Manager", "GodownKeeper", "KYCStaff",
                "Mechanic", "Owner", "DeliveryPerson", "CustomerCare"
        );
        for (String role : request.getRoles()) {
            if (!validRoles.contains(role)) {
                throw new BusinessException("Invalid role: " + role);
            }
        }

        employee.setDesignation(request.getDesignation());
        employee.setRoles(request.getRoles());
        employee.setIsActive(request.getIsActive());
        employee.setCareof(request.getCareof());
        employee.setVillage(request.getVillage());
        employee.setPanchayat(request.getPanchayat());
        employee.setDistrict(request.getDistrict());
        employee.setWardNo(request.getWardNo());
        employee.setMobile(request.getMobile());
        employee.setAadharNumber(request.getAadharNumber());
        employee = employeeRepository.save(employee);
        return employeeMapper.toResponse(employee);
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeResponse getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        return employeeMapper.toResponse(employee);
    }

//    @Override
//    @Transactional(readOnly = true)
//    public Page<EmployeeResponse> getAllEmployees(Pageable pageable) {
//        return employeeRepository.findAll(pageable)
//                .map(employeeMapper::toResponse);
//    }

//    @Override
//    @Transactional(readOnly = true)
//    public Page<EmployeeResponse> searchEmployees(String search, Pageable pageable) {
//        return employeeRepository.searchEmployees(search, pageable)
//                .map(employeeMapper::toResponse);
//    }


//    @Override
//    @Transactional
//    public void deactivateEmployee(Long id) {
//        Employee employee = employeeRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
//        employee.setIsActive(false);
//        employeeRepository.save(employee);
//    }

//    @Override
//    @Transactional
//    public void reactivateEmployee(Long id) {
//        Employee employee = employeeRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
//        employee.setIsActive(true);
//        employeeRepository.save(employee);
//    }

//    @Override
//    @Transactional(readOnly = true)
//    public List<EmployeeResponse> getActiveEmployeesByRole(String role) {
//        return employeeRepository.findByRoleAndIsActiveTrue(role)
//                .stream()
//                .map(employeeMapper::toResponse)
//                .collect(Collectors.toList());
//    }
}