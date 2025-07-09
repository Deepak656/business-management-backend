package com.example.lpgmanagement.services.validation;

import com.example.lpgmanagement.repositories.customer.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsumerNumberValidationService {
    private final CustomerRepository customerRepository;

    public boolean isValidConsumerNumber(Long consumerNumber) {
        if (consumerNumber == null || consumerNumber.toString().trim().isEmpty()) {
            return false;
        }

        // Check format using regex
        if (!consumerNumber.toString().trim().matches("^\\d{10}$")) {
            return false;
        }

        // Check if consumer exists and is valid government scheme customer
        return customerRepository.isSchemeValidCustomer(consumerNumber);
    }
}
