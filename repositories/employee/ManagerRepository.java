package com.example.lpgmanagement.repositories.employee;

import com.example.lpgmanagement.models.employee.types.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
}
