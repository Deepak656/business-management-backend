//package com.example.lpgmanagement.models.delivery;
//
//import com.example.lpgmanagement.models.base.BaseEntity;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotBlank;
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "delivery_verification")
//@Data
//@EqualsAndHashCode(callSuper = true)
//public class DeliveryVerification  extends BaseEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    // Fetch Consumer from UjjawalaDisptach
//    @NotBlank(message = "Consumer number is required")
//    @Column(name = "consumer_number", nullable = false)
//    private Long consumerNumber;
//
//    @Column(name = "aadhar_number")
//    private Long aadharNumber;
//
//    // Verification Details
//    //Session Details and timestamp and mark verified_editable as false
//    @Column(name = "delivery_verified_id")
//    private Long deliveryVerifiedById;
//
//    @Column(name = "delivery_verified_name")
//    private String deliveryVerifiedByName;
//
//    @Column(name = "delivery_verified_date")
//    private LocalDateTime deliveryVerifiedDate;
//
//    @Column(name = "verified_editable")
//    private boolean verified_editable = true;
//
//    // Select from Dropdown
//    @Column(name = "delivery_verified_method")
//    @Enumerated(EnumType.STRING)
//    private DeliveryConfirmMethod deliveryVerifiedByMethod = DeliveryConfirmMethod.PENDING;
//
//
//}
