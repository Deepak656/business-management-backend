package com.example.lpgmanagement.controllers.dispatch;

import com.example.lpgmanagement.dto.dispatch.*;
import com.example.lpgmanagement.dto.response.ApiResponse;
import com.example.lpgmanagement.models.dispatch.InvoiceStatus;
import com.example.lpgmanagement.models.dispatch.UjjwalaDispatch;
import com.example.lpgmanagement.security.CurrentUser;
import com.example.lpgmanagement.security.UserPrincipal;
import com.example.lpgmanagement.services.dispatch.UjjwalaDispatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/dispatches")
@RequiredArgsConstructor
public class UjjwalaDispatchController {

    @Autowired
    private final UjjwalaDispatchService ujjwalaDispatchService;




    @PutMapping("/{id}/assign-mechanic")
    @PreAuthorize("hasAnyRole('ROLE_Admin', 'MANAGER')")
    public ResponseEntity<ApiResponse<AssignMechanicResponse>> assignMechanic(
            @Valid @RequestBody AssignMechanicRequest request,
            @CurrentUser UserPrincipal currentUser) {

        // Call the service and get the response
        AssignMechanicResponse response = ujjwalaDispatchService.assignMechanic(request, currentUser);

        // Wrap in ApiResponse and return
        return ResponseEntity.ok(ApiResponse.success(response));
    }


    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_Admin', 'MANAGER', 'GODOWN_KEEPER', 'MECHANIC')")
    public ResponseEntity<Page<UjjwalaDispatch>> searchDispatches(
            @RequestParam(required = false) InvoiceStatus status,
            @RequestParam(required = false) String mechanicAssignedId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            Pageable pageable) {

        return ResponseEntity.ok(ujjwalaDispatchService.searchDispatches(status, mechanicAssignedId, startDate, endDate, pageable));
    }

    @GetMapping("/mechanic")
    @PreAuthorize("hasRole('MECHANIC')")
    public ResponseEntity<Page<UjjwalaDispatch>> getMechanicDispatches(
            @CurrentUser UserPrincipal currentUser,
            @RequestParam(required = false) InvoiceStatus status,
            Pageable pageable) {

        return ResponseEntity.ok(ujjwalaDispatchService.getMechanicDispatches(
                currentUser.getId(),
                status,
                pageable
        ));
    }

}