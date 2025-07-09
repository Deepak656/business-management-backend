package com.example.lpgmanagement.repositories.employee;

import com.example.lpgmanagement.models.employee.types.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
}
