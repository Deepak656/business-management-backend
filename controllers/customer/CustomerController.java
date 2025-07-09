package com.example.lpgmanagement.controllers.customer;

import com.example.lpgmanagement.dto.customer.CustomerResponse;
import com.example.lpgmanagement.dto.customer.CustomerUpdateRequest;
import com.example.lpgmanagement.dto.response.ApiResponse;
import com.example.lpgmanagement.models.customer.UjjwalaImportMaster;
import com.example.lpgmanagement.models.customer.UjjwalaImportMasterDetails;
import com.example.lpgmanagement.security.CurrentUser;
import com.example.lpgmanagement.security.UserPrincipal;
import com.example.lpgmanagement.services.customer.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.Valid;
import java.util.List; // Add this import

@RestController
@RequestMapping("/api/ujjwala_customer_master_details")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerServiceImpl customerService;

    @PostMapping("/upload")
    @PreAuthorize("hasRole('ROLE_Admin')")
    public ResponseEntity<ApiResponse<String>> uploadCustomerData(
            @RequestParam("file") MultipartFile file,
            @CurrentUser UserPrincipal currentUser) {
        try {
            customerService.processAndSaveCustomerData(file);
            return ResponseEntity.ok(ApiResponse.success("File uploaded and processed successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Failed to process file: " + e.getMessage()));
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_Admin')")
    public ResponseEntity<List<UjjwalaImportMaster>> getAllCustomers(@CurrentUser UserPrincipal currentUser) {
        List<UjjwalaImportMaster> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @PutMapping("/{id}/update-details")
    @PreAuthorize("hasAnyRole('ROLE_Admin', 'MANAGER')")
    public ResponseEntity<ApiResponse<CustomerResponse>> updateCustomer(
            @Valid @RequestBody CustomerUpdateRequest request,
            @CurrentUser UserPrincipal currentUser) {
        CustomerResponse updatedDetails = customerService.updateCustomer(request, currentUser);
        ApiResponse<CustomerResponse> response = ApiResponse.success(updatedDetails);
        return ResponseEntity.ok(response);
    }

    // Commented search endpoint preserved for future use
    /*
    @GetMapping("/search")
    public ResponseEntity<Page<CustomerResponse>> searchCustomers(
            @RequestParam(value = "search", required = false, defaultValue = "") String search,
            @PageableDefault(size = 20, sort = "name") Pageable pageable,
            @AuthenticationPrincipal UserPrincipal currentUser
    ) {
        Page<CustomerResponse> customers = customerService.searchCustomers(search, pageable, currentUser);
        return ResponseEntity.ok(customers);
    }
    */
}