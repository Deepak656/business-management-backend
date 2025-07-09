package com.example.lpgmanagement.dto.auth;

import lombok.Data;

import java.util.Set;

@Data
public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private String username;
    private String roles;
    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}