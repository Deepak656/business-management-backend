package com.example.lpgmanagement.repositories.employee;

import com.example.lpgmanagement.models.employee.types.Mechanic;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MechanicRepository extends JpaRepository<Mechanic, Long> {
    List<Mechanic> findByIsActiveTrue();

    Optional<Mechanic> findByLicenseNumber(String licenseNumber);
}
