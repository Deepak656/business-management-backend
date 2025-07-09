package com.example.lpgmanagement.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.jwt")
@Data
public class JwtConfig {
    private String secret;
    private long expirationInMs;
    private String tokenPrefix = "Bearer ";
    private String headerString = "Authorization";
}