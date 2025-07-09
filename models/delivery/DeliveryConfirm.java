//package com.example.lpgmanagement.models.delivery;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotBlank;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//
//public class DeliveryConfirm {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    //Details from UjjawalaDisptach
//    @NotBlank(message = "Consumer number is required")
//    @Column(name = "consumer_number", nullable = false)
//    private Long consumerNumber;
//    @Column(name = "aadhar_number")
//    private Long aadharNumber;
//    @Column(name = "mechanic_name")
//    private LocalDateTime actualDeliveryByName;
//    @Column(name = "mechanic_id")
//
//    // DELIVERY - Mechanic or Godown Keeper
//    private Long actualDeliveryById;
//    @Column(name = "customer_mobile_during_delivery")
//    private Long customerMobileByDeliveryBoy;
//    @Column(name = "customer_ward_during_delivery")
//    private Long customerWardNoByDeliveryBoy;
//    @Column(name = "customer_aadhar_number_during_delivery")
//    private Long customerAadharNumberDuringDelivery;
//    @Column(name = "delivered_to_relation")
//
//    private DeleveredTo deliveredTo = DeleveredTo.BLANK;
//
//    // If not DeleveredTo is not SELF then fill this details
//    @Column(name = "delivered_to_aadhar")
//    private Long deliveredToAadhar;
//    @Column(name = "delivered_to_name")
//    private String deliveredToName;
//    @Column(name = "delivered_to_father_name")
//    private String deliveredToFatherName;
//    @Column(name = "mechanic_delivery_date")
//    private LocalDateTime actualDeliveryDate;
//
//    // Prevent editing in future
//    @Column(name = "actual_delivery_editable")
//    private boolean actualDeliveryEditable = true;
//    // Status
//    @Column(name = "delivery_status")
//    @Enumerated(EnumType.STRING)
//    private DeliveryStatus deliveryStatus = DeliveryStatus.PENDING;
//}
