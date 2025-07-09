package com.example.lpgmanagement.controllers.auth;

import com.example.lpgmanagement.dto.auth.JwtAuthenticationResponse;
import com.example.lpgmanagement.dto.auth.LoginRequest;
import com.example.lpgmanagement.security.SessionHandler;
import com.example.lpgmanagement.services.auth.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@Tag(name = "Authentication", description = "Authentication Management")
public class AuthController {
    private final SessionHandler sessionHandler;
    private final AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "User Login", description = "Authenticate user and return JWT token")
    public ResponseEntity<JwtAuthenticationResponse> login(@Valid @RequestBody LoginRequest loginRequest, HttpServletRequest request ) {
        log.debug("Received login request for path: {}", request.getRequestURI());
        log.debug("Request method: {}", request.getMethod());
        log.debug("Content type: {}", request.getContentType());
        try {
            log.info("Login attempt for username: {}", loginRequest.getUsername());
            JwtAuthenticationResponse response = authService.authenticate(loginRequest);
            log.info("Login successful for username: {}", loginRequest.getUsername());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Login failed for username: {}", loginRequest.getUsername(), e);
            throw e;
        }
    }


    @PostMapping("/logout")
    public ResponseEntity logout(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        sessionHandler.handleLogout(authHeader);
        return ResponseEntity.ok().build();
    }
}