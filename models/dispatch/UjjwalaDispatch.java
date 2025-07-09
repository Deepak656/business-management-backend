package com.example.lpgmanagement.models.dispatch;

import com.example.lpgmanagement.models.godownissue.GodownStatus;
import com.example.lpgmanagement.models.godownissue.ProductIssuedFrom;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "ujjwala_dispatch")
@Data
@EqualsAndHashCode(callSuper = false)
public class UjjwalaDispatch {
    // Customer Details - Admin
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "ucode")
    private Long ucode;

    @NotBlank(message = "Consumer number is required")
    @Column(name = "consumer_number", nullable = false)
    private Long consumerNumber;

    @Column(name = "aadhar_number", columnDefinition = "bigint")
    private Long aadharNumber;

    //Verifying Aadhar, Mobile, Ward during Invoicing
    @Column(name = "aadhar_number_during_invoice")
    private Long aadharNumberDuringInvoice;

    @NotBlank(message = "Invoice number is required")
    @Column(name = "invoice_number", nullable = false)
    private Long invoiceNumber;

    @Column(name = "customer_mobile_during_invoice")
    private Long customerMobileDuringInvoice;

    @Column(name = "customer_wardno_during_invoice")
    private Long customerWardNoDuringInvoice;

    @Column( name = "disptach_to")
    private DispatchTo dispatchTo = DispatchTo.BLANK;

    @NotNull(message = "Invoice date is required")
    @Column(name = "invoice_date", nullable = false)
    private LocalDateTime invoiceDate;

    @Column(name = "mechanic_assigned_id")
    private Long mechanicAssignedId;

    @Column(name = "mechanic_assigned_name")
    private String mechanicAssignedName;

    @Column(name = "invoice_issued_by_name")
    private String invoiceIssuedByName;

    @Column(name = "invoice_issued_by_id")
    private Long invoiceIssuedById;

    @Column(name = "invoice_editable")
    private boolean invoiceEditable = true;

    @Column(name = "invoice_status")
    private InvoiceStatus invoiceStatus = InvoiceStatus.PENDING ;

}

