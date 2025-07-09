package com.example.lpgmanagement.dto.godownissue;

import com.example.lpgmanagement.models.godownissue.ProductIssuedFrom;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GodownIssueResponse {
    private Long ucode;
    private Long consumerNumber;
    private String godownStatus;
    private Long invoiceNumber;
    private LocalDateTime invoiceDate;
    private String invoiceIssuedByName;
    private Long invoiceIssuedById;
    private Long mechanicAssignedId;
    private String mechanicAssignedName;
    private LocalDateTime godownIssueDate;
    private String godownIssuedByName;
    private Long godownIssuedById;
    private ProductIssuedFrom productIssuedFrom;
}
