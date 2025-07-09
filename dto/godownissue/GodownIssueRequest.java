package com.example.lpgmanagement.dto.godownissue;

import com.example.lpgmanagement.models.godownissue.GodownStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GodownIssueRequest {
    private Long ucode;
    private Long consumerNumber;
    private Long aadharNumber;
    private String name;
    private String careof;
    private Long wardNo;
    private String village;
    private String panchayat;
    private String district;
    private Long mobile;
    private Long invoiceNumber;
    private LocalDateTime invoiceDate;
    private Long mechanicAssignedId;
    private String mechanicAssignedName;
    private String productIssuedFrom;
    private String invoiceIssuedByName;
    private Long invoiceIssuedById;
    private Long mechanicAadharDuringGodown;
    private Long mechanicMobileDuringGodown;
    private String godownIssuedByName;
    private Long godownIssuedById;
    private LocalDateTime godownIssueDate;
    private GodownStatus godownStatus;
}
