package com.example.lpgmanagement.services.customer;

import com.example.lpgmanagement.models.customer.UjjwalaImportMaster;
import com.example.lpgmanagement.models.customer.UjjwalaImportMasterDetails;
import com.example.lpgmanagement.dto.customer.*;
import com.example.lpgmanagement.exception.*;
import com.example.lpgmanagement.mapper.CustomerMapper;
import com.example.lpgmanagement.repositories.customer.*;
import com.example.lpgmanagement.security.UserPrincipal;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final UjjwalaImportMasterRepository importRepository;
    private final CustomerMapper customerMapper;



    @Override
    @Transactional
    public void processAndSaveCustomerData(MultipartFile file) {
        log.info("Starting to process CSV file: {}", file.getOriginalFilename());

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            // First, parse and save to UjjwalaImportMaster
            CsvToBean<UjjwalaImportMaster> csvToBean = new CsvToBeanBuilder<UjjwalaImportMaster>(reader)
                    .withType(UjjwalaImportMaster.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withSeparator(',')
                    .build();

            List<UjjwalaImportMaster> importRecords = csvToBean.parse();
            validateImportData(importRecords);
            List<UjjwalaImportMaster> savedImports = importRepository.saveAll(importRecords);

            // Then create corresponding records in UjjwalaImportMasterDetails
            List<UjjwalaImportMasterDetails> detailsList = new ArrayList<>();
            for (UjjwalaImportMaster importRecord : savedImports) {
                UjjwalaImportMasterDetails details = createDetailsFromImport(importRecord);
                detailsList.add(details);
            }

            customerRepository.saveAll(detailsList);
            log.info("Successfully processed {} records", importRecords.size());
        } catch (Exception e) {
            log.error("Failed to process CSV file", e);
            throw new RuntimeException("Failed to process CSV file: " + e.getMessage());
        }
    }

    private void validateImportData(List<UjjwalaImportMaster> records) {
        List<String> errors = new ArrayList<>();
        int rowNumber = 1;

        for (UjjwalaImportMaster record : records) {
            if (record.getConsumerNumber() == null) {
                errors.add("Row " + rowNumber + ": Consumer number is required");
            }
            if (record.getSchemeType() == null || record.getSchemeType().trim().isEmpty()) {
                errors.add("Row " + rowNumber + ": Scheme type is required");
            }
            if (record.getName() == null || record.getName().trim().isEmpty()) {
                errors.add("Row " + rowNumber + ": Name is required");
            }
            rowNumber++;
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(String.join("; ", errors));
        }
    }

    private UjjwalaImportMasterDetails createDetailsFromImport(UjjwalaImportMaster importRecord) {
        UjjwalaImportMasterDetails details = new UjjwalaImportMasterDetails();
        details.setConsumerNumber(importRecord.getConsumerNumber());
        details.setSchemeType(importRecord.getSchemeType());
        details.setName(importRecord.getName());
        details.setAddress(importRecord.getAddress());
        // Handle mobile conversion from String to Long
        if (importRecord.getMobile() != null && !importRecord.getMobile().trim().isEmpty()) {
            try {
                details.setMobile(Long.parseLong(importRecord.getMobile()));
            } catch (NumberFormatException e) {
                log.warn("Invalid mobile number for record: {}", importRecord);
                details.setMobile(null); // Or handle the invalid case as needed
            }
        } else {
            details.setMobile(null); // Or provide a default value if required
        }        details.setIsDeleted(false);
        return details;
    }

    @Override
    @Transactional
    public CustomerResponse createCustomer(CustomerCreateRequest request, UserPrincipal currentUser) {
        log.info("Creating new customer with consumer number: {}", request.getConsumerNumber());

        if (customerRepository.findByConsumerNumberAndIsDeletedFalse(request.getConsumerNumber()).isPresent()) {
            throw new DuplicateResourceException("Customer with consumer number already exists: " + request.getConsumerNumber());
        }

        UjjwalaImportMasterDetails customer = customerMapper.toEntity(request);
        customer = customerRepository.save(customer);

        log.info("Created new customer with ConsumerNumber: {}", customer.getConsumerNumber());
        return customerMapper.toResponse(customer);
    }

    @Override
    @Transactional
    public CustomerResponse updateCustomer(CustomerUpdateRequest request, UserPrincipal currentUser) {
        Long consumerNumber = request.getConsumerNumber();
        log.info("Updating customer with ConsumerNumber: {}", consumerNumber);

        UjjwalaImportMasterDetails customer = customerRepository.findByConsumerNumber(consumerNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ConsumerNumber: " + consumerNumber));

        customer.setCustomerModifiedDate(LocalDateTime.now());
        customer.setCareof(request.getCareOf());
        customer.setVillage(request.getVillage());
        customer.setPanchayat(request.getPanchayat());
        customer.setDistrict(request.getDistrict());
        customer.setWardNo(request.getWardNo());
        customer.setMobile(request.getMobile());
        customer.setAadharNumber(request.getAadhar());

        customer = customerRepository.save(customer);
        log.info("Updated customer with ConsumerNumber: {}", consumerNumber);
        return customerMapper.toResponse(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerResponse getCustomerById(Long id, UserPrincipal currentUser) {
        log.debug("Fetching customer with ID: {}", id);

        UjjwalaImportMasterDetails customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));

        return customerMapper.toResponse(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isSchemeValidCustomer(Long consumerNumber, UserPrincipal currentUser) {
        if (consumerNumber == null) {
            return false;
        }
        return customerRepository.isSchemeValidCustomer(consumerNumber);
    }
    @Override
    public List<UjjwalaImportMaster> getAllCustomers() {
        return importRepository.findAll();
    }
}