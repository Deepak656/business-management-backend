package com.example.lpgmanagement.models.customer;

import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "ujjwala_customer_master_details")
@Data
@EqualsAndHashCode()
public class UjjwalaImportMasterDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Consumer number is required")
    @Column(name = "consumer_number", nullable = false, unique = true)
    private Long consumerNumber;

    @NotBlank(message = "Aadhar number is required")
    @Column(nullable = false, unique = true)
    private Long aadharNumber;

    @Column(name = "scheme_type")
    private String schemeType;

    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    private String name;

    @Column(name = "careof")
    private String careof;

    @Column(name = "ward_no")
    private Long wardNo;

    @Column(name = "village")
    private String village;

    @Column(name = "panchayat")
    private String panchayat;

    @Column(name = "district")
    private String district;

    @Column(name = "address")
    private String address;

    @Column(name = "mobile")
    private Long mobile;

    @Column(name = "customer_created_by_id")
    private Long CustomerCreatedByID;
    @Column(name = "customer_created_by_name")
    private String CustomerCreateByName;
    @Column(name = "customer_modified_date")
    private LocalDateTime CustomerModifiedDate;
    @Column(name = "customer_created_date")
    private LocalDateTime CustomerCreateDate;
    @Column(name = "is_deleted")
    Boolean isDeleted;
    @Column(name = "deleted_by")
    private Long deletedById;
    @Column(name = "deleted_date")
    private LocalDateTime deletedDate;


}