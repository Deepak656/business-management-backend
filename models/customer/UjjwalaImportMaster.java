package com.example.lpgmanagement.models.customer;

import lombok.Data;

import jakarta.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
// Data obtained by uploading csv file from SDMS

@Entity
@Table(name = "ujjawala_customer_master")
@Data
//@EqualsAndHashCode(callSuper = true)
public class UjjwalaImportMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Consumer number is required")
    @Column(name = "consumer_number", nullable = false, unique = true)
    private Long consumerNumber;
    @NotBlank(message = "Scheme type is required")
    @Column(name = "scheme_type", nullable = false)
    private String SchemeType;

    @NotBlank(message = "Name is required")
    @Column(name = "sdms_name", nullable = false)
    private String name;

    @Column(name = "sdms_address")
    private String Address;

    @Column(name = "sdms_mobile")
    private String Mobile;

    @NotNull(message = "KYC date is required")
    @Column(name = "sdms_kyc_date", nullable = false)
    private LocalDate KycDate;

    @Column(name = "sdms_sv_issue_date")
    private LocalDate SvIssueDate;

    @Column(name = "sdms_order_date")
    private LocalDate OrderDate;

}
