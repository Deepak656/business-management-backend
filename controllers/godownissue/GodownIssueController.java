package com.example.lpgmanagement.controllers.godownissue;

import com.example.lpgmanagement.models.dispatch.InvoiceStatus;
import com.example.lpgmanagement.models.godownissue.GodownIssue;
import com.example.lpgmanagement.models.godownissue.GodownStatus;
import com.example.lpgmanagement.security.CurrentUser;
import com.example.lpgmanagement.security.UserPrincipal;
import com.example.lpgmanagement.services.godownissue.GodownIssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/godownissue")
@RequiredArgsConstructor
public class GodownIssueController {

    @Autowired
    private final GodownIssueService godownIssueService;

    @PostMapping("/{id}/godown-issue")
    @PreAuthorize("hasRole('GODOWN_KEEPER')")
    public ResponseEntity<GodownIssue> createGodownIssue(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime issueDate,
            @CurrentUser UserPrincipal currentUser) {

        ResponseEntity<GodownIssue> response = ResponseEntity.ok(godownIssueService.updateGodownIssue(
                id,
                issueDate,
                currentUser.getId(),
                currentUser.getName()
        ));
        return response;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_Admin', 'MANAGER', 'GODOWN_KEEPER', 'MECHANIC')")
    public ResponseEntity<Page<GodownIssue>> searchGodownIssueByInvoiceDate(
            @RequestParam(required = false) GodownStatus status,
            @RequestParam(required = false) String mechanicAssignedId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            Pageable pageable) {
        return ResponseEntity.ok(godownIssueService.searchGodownIssueByInvoiceDate(status, startDate, endDate, pageable));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_Admin', 'GODOWN_KEEPER')")
    public ResponseEntity<GodownIssue> GetGodownIssueById(Long id,
                                                          Pageable pageable) {
        return ResponseEntity.ok(godownIssueService.getGodownIssueById(id));
    }

}