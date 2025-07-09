package com.example.lpgmanagement.repositories.dispatch;

import com.example.lpgmanagement.models.dispatch.InvoiceStatus;
import com.example.lpgmanagement.models.dispatch.UjjwalaDispatch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.DoubleStream;

@Repository
public interface UjjwalaDispatchRepository extends JpaRepository<UjjwalaDispatch, Long> {

//    @Query("SELECT d FROM UjjwalaDispatch d WHERE " +
//            "d.ConsumerNumber = :ConsumerNo")
//    List<UjjwalaDispatch> findByConsumerNumber(Long consumerNo);

    @Query("SELECT d FROM UjjwalaDispatch d WHERE " +
            "(:status IS NULL OR d.invoiceStatus = :status) AND " +
            "(:mechanicId IS NULL OR d.mechanicAssignedId = :mechanicId) AND " +
            "(:startDate IS NULL OR d.invoiceDate >= :startDate) AND " +
            "(:endDate IS NULL OR d.invoiceDate <= :endDate)")
    Page<UjjwalaDispatch> searchDispatches(
            @Param("status") InvoiceStatus status,
            @Param("mechanicId") String mechanicId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            Pageable pageable);

//    @Query("SELECT d FROM UjjwalaDispatch d WHERE " +
//            "LOWER(d.consumerNumber) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
//            "LOWER(d.name) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
//            "LOWER(d.invoiceNumber) LIKE LOWER(CONCAT('%', :search, '%'))")
//    Page<UjjwalaDispatch> searchByKeyword(@Param("search") String search, Pageable pageable);

    @Query("SELECT d FROM UjjwalaDispatch d WHERE " +
            "d.consumerNumber = :consumerNo AND d.invoiceStatus = :status")
    boolean existsByConsumerNumberAndStatusNot(Long consumerNo, InvoiceStatus status);

//    List<UjjwalaDispatch> findByConsumerNumberContainingOrNameContaining(String query, String query1);
//    List<UjjwalaDispatch> findByInvoiceDateBetween(LocalDate startDate, LocalDate endDate);
//    List<UjjwalaDispatch> findByMechanicDeliveryDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT d FROM UjjwalaDispatch d WHERE " +
            "(:status IS NULL OR d.invoiceStatus = :status) AND " +
            "(:mechanicId IS NULL OR d.mechanicAssignedId = :mechanicId) ")
    Page<UjjwalaDispatch> findByMechanicIdAndStatus(Long mechanicId, InvoiceStatus status, Pageable pageable);

    @Query("SELECT d from UjjwalaDispatch d WHERE d.consumerNumber = :consumerNumber")
    Optional <UjjwalaDispatch> findByConsumerNumber(@Param("consumerNumber") Long consumerNumber);

}