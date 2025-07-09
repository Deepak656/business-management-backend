package com.example.lpgmanagement.dto.dispatch;

import lombok.Data;

@Data
public class AssignMechanicResponse {
    private Long id;
    private Long consumerNumber;
    private Long aadharNumber;
    private Long ucode;
    private String invoiceStatus;
    private Long invoiceNumber;
    private String invoiceDate;
    private String invoiceIssuedByName;
    private Long invoiceIssuedById;
    private Long mechanicAssignedId;
    private String mechanicAssignedName;
    private Boolean invoiceEditable;
}
