package com.example.lpgmanagement.dto.customer;

import com.example.lpgmanagement.dto.base.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerCreateRequest extends BaseRequest {
    @NotBlank(message = "Consumer number is required")
    private Long consumerNumber;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Care of is required")
    private String careof;

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

    @NotNull(message = "KYC date is required")
    private LocalDate kycDate;
    private LocalDate svIssueDate;
    private LocalDate orderDate;
    @NotBlank(message = "Scheme type is required")
    private String schemeType;
}
