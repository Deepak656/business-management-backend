package com.example.lpgmanagement.controllers.admin;

import com.example.lpgmanagement.dto.access.TableAccessControlResponse;
import com.example.lpgmanagement.services.access.TableAccessControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private TableAccessControlService tableAccessControlService;

    @GetMapping("/table-access")
    @PreAuthorize("hasRole('ROLE_Admin')")
    public ResponseEntity<List<TableAccessControlResponse>> getTableAccess() {
        ResponseEntity<List<TableAccessControlResponse>> responses =  ResponseEntity.ok(tableAccessControlService.getAllTableAccess());
        return responses;
    }
}