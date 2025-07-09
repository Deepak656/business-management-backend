package com.example.lpgmanagement.repositories.employee;

import com.example.lpgmanagement.models.employee.types.GodownKeeper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// GodownKeeperRepository.java
@Repository
public interface GodownKeeperRepository extends JpaRepository<GodownKeeper, Long> {
    Optional<GodownKeeper> findByGodownCode(String godownCode);
}
