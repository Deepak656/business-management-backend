package com.example.lpgmanagement.models.document;

import com.example.lpgmanagement.models.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.*;
import javax.validation.constraints.NotBlank;
@Entity
@Table(name = "document_mapping_details")
@Data
@EqualsAndHashCode(callSuper = true)
public class DocumentMappingDetails extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Consumer number is required")
    @Column(name = "consumer_number", nullable = true, unique = true)
    private String consumerNumber;

    @NotBlank(message = "Aadhar number is required")
    @Column(nullable = true, unique = false)
    private String aadharNumber;

    @NotBlank(message = "Ration number is required")
    @Column(nullable = true)
    private String rationNumber;

    @Column(name = "customer_mobile")
    private String customer_mobile;

    @Column(name = "ward_no")
    private String wardNo;

    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    private String name;

    @Column(name = "careof")
    private String careof;

    @Column(name = "village")
    private String village;

    @Column(name = "panchayat")
    private String panchayat;

    @Column(name = "district")
    private String district;

    @Column(name = "address")
    private String address;

    @Column(name = "document_collected_by_id")
    private String documentCollectedById;

    @Column(name = "document_collected_by_name")
    private String documentCollectedByName;

    @Column(name = "document_collected_by_date")
    private String documentCollectedByDate;

    @Column(name = "kyc_by_id")
    private String kycDonebyId;

    @Column(name = "kyc_by_name")
    private String kycDonebyName;

    @Column(name = "kyc_done_date")
    private String kycDoneDate;

}
