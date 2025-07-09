package com.example.lpgmanagement.security;

import com.example.lpgmanagement.repositories.auth.UserSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class SessionHandler {
    private final UserSessionRepository userSessionRepository;
    private static final String BEARER_PREFIX = "Bearer ";

    public void handleLogout(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith(BEARER_PREFIX)) {
            String token = authorizationHeader.substring(BEARER_PREFIX.length());
            userSessionRepository.findBySessionToken(token)
                    .ifPresent(session -> {
                        session.setIsActive(false);
                        session.setLogoutTime(LocalDateTime.now());
                        userSessionRepository.save(session);
                    });
        }
    }
}