package com.example.lpgmanagement.repositories.customer;

import com.example.lpgmanagement.models.customer.UjjwalaImportMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UjjwalaImportMasterRepository extends JpaRepository<UjjwalaImportMaster, Long> {
    Optional<UjjwalaImportMaster> findByConsumerNumber(Long consumerNumber);
}

