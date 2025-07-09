package com.example.lpgmanagement.dto.customer;

import com.example.lpgmanagement.dto.base.BaseRequest;
import com.example.lpgmanagement.dto.base.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode()
public class CustomerResponse {
    private Long id;
    private String consumerNumber;
    private String name;
    private String careof;
    private Long wardNo;
    private String village;
    private String panchayat;
    private String district;
    private Long mobile;
    private Long aadhar;
    private LocalDate kycDate;
    private LocalDate svIssueDate;
    private LocalDate orderDate;
    private String schemeType;
    private Boolean deleted;
}