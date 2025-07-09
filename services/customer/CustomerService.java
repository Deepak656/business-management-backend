package com.example.lpgmanagement.services.customer;

import com.example.lpgmanagement.dto.customer.*;
import com.example.lpgmanagement.models.customer.UjjwalaImportMaster;
import com.example.lpgmanagement.security.UserPrincipal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerCreateRequest request, UserPrincipal currentUser);

    CustomerResponse updateCustomer(CustomerUpdateRequest request, UserPrincipal currentUser);

    CustomerResponse getCustomerById(Long id, UserPrincipal currentUser);

    @Transactional(readOnly = true)
    boolean isSchemeValidCustomer(@NotBlank(message = "Consumer number is required") Long consumerNumber, UserPrincipal currentUser);

    void processAndSaveCustomerData(MultipartFile file);

    List<UjjwalaImportMaster> getAllCustomers();
//    Page<CustomerResponse> getAllCustomers(Pageable pageable, UserPrincipal currentUser);

//    Page<CustomerResponse> searchCustomers(String search, Pageable pageable, UserPrincipal currentUser);

//    void deleteCustomer(Long customerId, UserPrincipal currentUser);

//    Page<CustomerResponse> getDeletedCustomers(Pageable pageable, UserPrincipal currentUser);

}
