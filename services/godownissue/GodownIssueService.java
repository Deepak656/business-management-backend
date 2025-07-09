package com.example.lpgmanagement.services.godownissue;

//import com.example.lpgmanagement.mapper.DispatchMapper;
import com.example.lpgmanagement.models.dispatch.InvoiceStatus;
import com.example.lpgmanagement.models.godownissue.GodownIssue;
import com.example.lpgmanagement.models.godownissue.GodownStatus;
import com.example.lpgmanagement.models.dispatch.UjjwalaDispatch;
import com.example.lpgmanagement.repositories.dispatch.UjjwalaDispatchRepository;
import com.example.lpgmanagement.repositories.godownissue.GodownIssueRepository;
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
public class GodownIssueService {
    @Autowired
    private final GodownIssueRepository godownIssueRepository;
    @Autowired
    private final UjjwalaDispatchRepository dispatchRepository;
    @Autowired
    private final CustomerService customerService;
//    @Autowired
//    private final DispatchMapper dispatchMapper;

    private final ConsumerNumberValidationService consumerNumberValidationService;

    public GodownIssue updateGodownIssue(Long id, LocalDateTime godownIssueDate, Long godownKeeperId, String godownKeeperName) {
        log.info("Updating godown issue for consumer number {}", id);
        GodownIssue godownissue = godownIssueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Don't Issue Product - Invoice not Issued"));

        if (!godownissue.isGodownEditable()) {
            throw new BusinessException("Not editable - Don't Issue Product- Contact Deepak");
        }

        godownissue.setGodownIssueDate(godownIssueDate);
        godownissue.setGodownIssuedById(godownKeeperId);
        godownissue.setGodownIssuedByName(godownKeeperName);
        godownissue.setGodownStatus(GodownStatus.GODOWN_ISSUED);
        godownissue = godownIssueRepository.save(godownissue);

//        return dispatchMapper.toGodownResponse(dispatch);
        return godownissue;
    }

    @Transactional(readOnly = true)
    public Page<GodownIssue> searchGodownIssueByInvoiceDate(
            GodownStatus godownStatus,
            LocalDate invoiceStartDate,
            LocalDate invoiceEndDate,
            Pageable pageable) {

        return godownIssueRepository.searchGodownIssueByInvoiceDate(godownStatus, invoiceStartDate, invoiceEndDate, pageable);
    }

    @Transactional(readOnly = true)
    public GodownIssue getGodownIssueById(Long id) {
        return godownIssueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dispatch not found"));
    }
}