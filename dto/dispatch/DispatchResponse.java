package com.example.lpgmanagement.dto.dispatch;

import com.example.lpgmanagement.models.godownissue.ProductIssuedFrom;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DispatchResponse {
    private Long id;
    private Long ucode;
    private Long consumerNumber;
    private Long aadharNumber;
    private Long mechanicAssignedId;
    private String mechanicAssignedName;
    private Long invoiceNumber;
    private LocalDateTime invoiceDate;
    private String invoiceStatus;
    private String invoiceIssuedByName;
    private Long invoiceIssuedById;
    private Boolean invoiceEditable;
    private LocalDateTime godownIssueDate;
    private String godownIssuedByName;
    private Long godownIssuedById;
    private String godownStatus;
    private Boolean godownEditable;
    private ProductIssuedFrom productIssuedFrom;
}
