package com.example.lpgmanagement.repositories.godownissue;

import com.example.lpgmanagement.models.dispatch.InvoiceStatus;
import com.example.lpgmanagement.models.godownissue.GodownIssue;
import com.example.lpgmanagement.models.godownissue.GodownStatus;
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
public interface GodownIssueRepository extends JpaRepository<GodownIssue, Long> {

    @Query("SELECT d FROM GodownIssue d WHERE " +
            "d.consumerNumber = :consumerNumber")
    List<GodownIssue> findByConsumerNumber(@Param("consumerNumber") Long consumerNumber);

    @Query("SELECT d FROM GodownIssue d WHERE " +
            "(:godownStatus IS NULL OR d.godownStatus = :godownStatus) AND " +
            "(:invoiceStartDate IS NULL OR d.invoiceDate >= :invoiceStartDate) AND " +
            "(:invoiceEndDate IS NULL OR d.invoiceDate <= :invoiceEndDate)")
    Page<GodownIssue> searchGodownIssueByInvoiceDate(
            @Param("godownStatus") GodownStatus godownStatus,
            @Param("invoiceStartDate") LocalDate invoiceStartDate,
            @Param("invoiceEndDate") LocalDate invoiceEndDate,
            Pageable pageable);

    @Query("SELECT CASE WHEN COUNT(d) > 0 THEN true ELSE false END " +
            "FROM GodownIssue d WHERE d.consumerNumber = :consumerNo AND d.godownStatus != :status")
    boolean existsByConsumerNumberAndStatus(@Param("consumerNo") Long consumerNo, @Param("status") GodownStatus status);

    @Query("SELECT d FROM GodownIssue d WHERE " +
            "(:status IS NULL OR d.godownStatus = :status) AND " +
            "(:godownKeeperId IS NULL OR d.godownIssuedById = :godownKeeperId) ")
    Page<GodownIssue> findByGodownKeeperIdAndStatus(Long godownKeeperId, GodownStatus status, Pageable pageable);

    @Query("SELECT consumerNumber FROM GodownIssue d WHERE d.id = :idd")
    DoubleStream findConsumerNumberById(Long idd);

    @Query("SELECT d FROM GodownIssue d WHERE d.id = :id")
    Optional<GodownIssue> findById(Long id);
}