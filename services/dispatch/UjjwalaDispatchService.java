package com.example.lpgmanagement.services.dispatch;

import com.example.lpgmanagement.dto.dispatch.*;
//import com.example.lpgmanagement.mapper.DispatchMapper;
import com.example.lpgmanagement.mapper.DispatchMapper;
import com.example.lpgmanagement.models.dispatch.InvoiceStatus;
import com.example.lpgmanagement.models.dispatch.UjjwalaDispatch;
import com.example.lpgmanagement.repositories.dispatch.UjjwalaDispatchRepository;
import com.example.lpgmanagement.security.UserPrincipal;
import com.example.lpgmanagement.services.validation.ConsumerNumberValidationService;
import com.example.lpgmanagement.exception.BusinessException;
import com.example.lpgmanagement.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.lpgmanagement.services.customer.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UjjwalaDispatchService {
    @Autowired
    private final UjjwalaDispatchRepository dispatchRepository;
    @Autowired
    private final CustomerService customerService;
    @Autowired
    private final DispatchMapper dispatchMapper;

    private final ConsumerNumberValidationService consumerNumberValidationService;

    @Transactional
    public AssignMechanicResponse assignMechanic(AssignMechanicRequest request, UserPrincipal currentUser ) {
        Long ConsumerNumber = request.getConsumerNumber();
        // Check Customer exists or not using id
        UjjwalaDispatch dispatch = dispatchRepository.findByConsumerNumber(ConsumerNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Dispatch not found"));

        log.info("Assigning mechanic {} to consumer {}", request.getMechanicAssignedName(), ConsumerNumber);

        // Validate consumer Number and Scheme Type
        if (!customerService.isSchemeValidCustomer(ConsumerNumber, currentUser)) {
            throw new BusinessException("Error2 - Not a Ujjwala Active Customer");
        }

        // Check if Mechanic Already Assigned and Invoice Issued
        if (dispatchRepository.existsByConsumerNumberAndStatusNot(ConsumerNumber, InvoiceStatus.MECHANIC_ASSIGNED)) {
            throw new BusinessException("Error3 - Already Invoiced");
        }

        // Check if record editable
        if (!dispatch.isInvoiceEditable()) {
            throw new BusinessException("Error4 - Record is not editable");
        }
        dispatch.setInvoiceDate(LocalDateTime.now());
        dispatch.setInvoiceIssuedByName(currentUser.getName());
        dispatch.setInvoiceIssuedById(currentUser.getId());
        dispatch.setMechanicAssignedId(request.getMechanicAssignedId());
        dispatch.setMechanicAssignedName(request.getMechanicAssignedName());
        dispatch.setInvoiceStatus(InvoiceStatus.MECHANIC_ASSIGNED);
        dispatch = dispatchRepository.save(dispatch);
        return dispatchMapper.toMechanicResponse(dispatch);
    }

    @Transactional(readOnly = true)
    public Page<UjjwalaDispatch> searchDispatches(
            InvoiceStatus status,
            String mechanicId,
            LocalDate startDate,
            LocalDate endDate,
            Pageable pageable) {

        return dispatchRepository.searchDispatches(status, mechanicId, startDate, endDate, pageable);
    }

    @Transactional(readOnly = true)
    public Page<UjjwalaDispatch> getMechanicDispatches(Long mechanicId, InvoiceStatus status, Pageable pageable) {
        Page<UjjwalaDispatch> dispatchPage = dispatchRepository.findByMechanicIdAndStatus(mechanicId, status, pageable);
//        return dispatchPage.map(dispatchMapper::toResponse);
        return dispatchPage;


    }
//    @Transactional(readOnly = true)
//    public ConsumerNumberResponse getDispatchConsumerNumber(Long consumerNumber) {
//        return dispatchRepository.findDispatchByConsumer(id)
//                .map(dispatchMapper::toConsumerNumberResponse)
//                .orElseThrow(() -> new ResourceNotFoundException("Dispatch not found"));
//    }

    @Transactional(readOnly = true)
    public UjjwalaDispatch getDispatchById(Long id) {
//        return dispatchRepository.findById(id)
//                .map(dispatchMapper::toResponse)
//                .orElseThrow(() -> new ResourceNotFoundException("Dispatch not found"));
        return dispatchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dispatch not found"));
    }
}