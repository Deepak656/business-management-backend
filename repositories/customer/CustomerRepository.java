package com.example.lpgmanagement.repositories.customer;

import com.example.lpgmanagement.dto.customer.CustomerResponse;
import com.example.lpgmanagement.models.customer.UjjwalaImportMasterDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<UjjwalaImportMasterDetails, Long> {

    // Find only not deleted customers
    @Query("SELECT d FROM UjjwalaImportMasterDetails d WHERE d.isDeleted = false")
    Page<UjjwalaImportMasterDetails> findByIsDeletedFalse(Pageable pageable);

    // Find deleted customers
    @Query("SELECT d FROM UjjwalaImportMasterDetails d WHERE d.isDeleted = true")
    Page<CustomerResponse> findByDeletedTrue(Pageable pageable);

//    @Query("SELECT c FROM UjjwalaImportMasterDetails c WHERE c.isDeleted = false AND " +
//            "(:search IS NULL OR :search = '' OR " +
//            "(LOWER(c.name) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
//            "LOWER(c.consumerNumber) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
//            "LOWER(c.mobile) LIKE LOWER(CONCAT('%', :search, '%')))")
//    Page<UjjwalaImportMasterDetails> searchCustomers(
//            @Param("search") String search,
//            Pageable pageable
//    );
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END FROM UjjwalaImportMasterDetails c " +
            "WHERE c.consumerNumber = :consumerNumber " +
            "AND c.schemeType = 'Central Govt Scheme' " +
            "AND c.isDeleted = false")
    boolean isSchemeValidCustomer(@Param("consumerNumber") Long consumerNumber);

    @Modifying
    @Query("UPDATE UjjwalaImportMasterDetails c SET c.isDeleted = true, c.deletedById = :deletedById, c.deletedDate = CURRENT_TIMESTAMP WHERE c.id = :id")
    void softDeleteById(@Param("id") Long id, @Param("deletedById") Long deletedById);

    List<UjjwalaImportMasterDetails> findByNameContainingOrConsumerNumberContaining(String query, String query1);

    @Query("SELECT d from UjjwalaImportMasterDetails d WHERE d.isDeleted = true")
    Page<CustomerResponse> getDeletedCustomers(Pageable pageable);

    @Query("SELECT d FROM UjjwalaImportMasterDetails d WHERE d.consumerNumber = :consumerNumber AND d.isDeleted = false")
    Optional<UjjwalaImportMasterDetails> findByConsumerNumber(@Param("consumerNumber") Long consumerNumber);
    @Query("SELECT d FROM UjjwalaImportMasterDetails d WHERE d.isDeleted = false AND d.consumerNumber = :consumerNumber")
    Optional<UjjwalaImportMasterDetails> findByConsumerNumberAndIsDeletedFalse(Long consumerNumber);
    //    void deleteByConsumerNumber(Long consumerNumber);

//    boolean existsByConsumerNumber(Long consumerNumber);


}