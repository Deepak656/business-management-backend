package com.example.lpgmanagement.dto.customer;

import lombok.Data;
import java.time.LocalDate;

@Data
public class CustomerDTO {
    private Long id;
    private Long consumerNumber;
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
}

