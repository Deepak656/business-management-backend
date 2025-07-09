package com.example.lpgmanagement.repositories.employee;

import com.example.lpgmanagement.models.employee.Employee;

import java.util.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmployeeCode(String employeeCode);

//    @Query("SELECT e FROM Employee e WHERE " +
//            "LOWER(e.name) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
//            "LOWER(e.employeeCode) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
//            "LOWER(e.mobile) LIKE LOWER(CONCAT('%', :search, '%'))")
//    Page<Employee> searchEmployees(String search, Pageable pageable);

    @Query("SELECT e FROM Employee e JOIN e.roles r WHERE r IN :roles")
    List<Employee> findByRoles(@Param("roles") Set<String> roles);

    List<Employee> findByIsActiveTrue();

    List<Employee> findByNameContainingOrEmployeeCodeContaining(String query, String query1);

    @Query("SELECT e FROM Employee e JOIN e.roles r WHERE r IN :roles AND e.isActive = true")
    Collection<Object> findByRolesAndIsActiveTrue(@Param("roles") Set<String> roles);
}

