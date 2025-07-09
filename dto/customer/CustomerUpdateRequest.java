package com.example.lpgmanagement.dto.customer;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
public class CustomerUpdateRequest {
    private String Id;
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Consumer Number is required")
    private Long ConsumerNumber;

    @NotBlank(message = "Care of is required")
    private String careOf;

    @NotBlank(message = "Ward number is required")
    private Long wardNo;

    @NotBlank(message = "Village is required")
    private String village;

    @NotBlank(message = "Panchayat is required")
    private String panchayat;

    @NotBlank(message = "District is required")
    private String district;

    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits")
    private Long mobile;

    @NotBlank(message = "Aadhar number is required")
    private Long aadhar;

    private String schemeType;
    private LocalDate kycDate;
    private LocalDate svIssueDate;
    private LocalDate orderDate;
}
