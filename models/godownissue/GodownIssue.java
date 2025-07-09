package com.example.lpgmanagement.models.godownissue;

import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "godown_ujjwala_issue")
@Data
@EqualsAndHashCode(callSuper = false)
public class GodownIssue {
    // Customer Details - Admin
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "dcode")
    private Long dcode;

    @NotBlank(message = "Consumer number is required")
    @Column(name = "consumer_number", nullable = false)
    private Long consumerNumber;

    @Column(name = "aadhar_number", columnDefinition = "bigint")
    private Long aadharNumber;

    @NotBlank(message = "Invoice number is required")
    @Column(name = "invoice_number", nullable = false)
    private Long invoiceNumber;

    @NotBlank(message = "dispatch id is required")
    @Column(name = "dispatch_id", nullable = false)
    private Long dispatchId;

    @NotNull(message = "Invoice date is required")
    @Column(name = "invoice_date", nullable = false)
    private LocalDateTime invoiceDate;

    // GDODOWN - Godown Keeper and Manager
    @Column(name = "product_issued_from")
    private ProductIssuedFrom productIssuedFrom = ProductIssuedFrom.BLANK;

    @Column(name = "mechanic_aadhar_during_godown")
    private String mechanicAadharDuringGodown;

    @Column(name = "mechanic_mobile_during_godown")
    private String mechanicMobileDuringGodown;

    @Column(name = "godown_issued_by_name")
    private String godownIssuedByName;

    @Column(name = "godown_issued_by_id")
    private Long godownIssuedById;

    @Column(name = "godown_issue_date")
    private LocalDateTime godownIssueDate;

    @Column(name = "godown_editable")
    private boolean godownEditable = true;

    @Column(name = "godown_status")
    private GodownStatus godownStatus = GodownStatus.PENDING;
}

