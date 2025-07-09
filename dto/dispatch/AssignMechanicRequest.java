package com.example.lpgmanagement.dto.dispatch;

import com.example.lpgmanagement.models.dispatch.DispatchTo;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AssignMechanicRequest {
    private Long ucode;
    private Long consumerNumber;
    private Long aadharNumber;
    private Long mobile;
    private Long aadharNumberDuringInvoice;
    private Long customerMobileDuringInvoice;
    private Long customerWardNoDuringInvoice;
    private DispatchTo dispatchTo;
    private Long invoiceNumber;
    private LocalDateTime invoiceDate;
    private Long mechanicAssignedId;
    private String mechanicAssignedName;
    private String invoiceIssuedByName;
    private Long invoiceIssuedById;
    private Boolean invoiceEditable = false;

}