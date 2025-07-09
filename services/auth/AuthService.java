package com.example.lpgmanagement.services.auth;

import com.example.lpgmanagement.dto.auth.JwtAuthenticationResponse;
import com.example.lpgmanagement.dto.auth.LoginRequest;
import com.example.lpgmanagement.models.auth.UserSession;
import com.example.lpgmanagement.repositories.auth.UserSessionRepository;
import com.example.lpgmanagement.security.UserPrincipal;
import com.example.lpgmanagement.security.jwt.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final UserSessionRepository userSessionRepository;

    public JwtAuthenticationResponse authenticate(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
            log.info("Authentication successful for user: {}", loginRequest.getUsername());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Generate JWT token
            String jwt = tokenProvider.generateToken(authentication);

            // Create and save user session
            UserSession userSession = new UserSession();
            userSession.setSessionId(UUID.randomUUID().toString());
            userSession.setSessionToken(jwt);
            userSession.setUserId(getUserIdFromAuthentication(authentication));
            userSession.setLoginTime(LocalDateTime.now());
            userSession.setLastActivityTime(LocalDateTime.now());
            userSession.setIsActive(true);

            userSessionRepository.save(userSession);

            // Create authentication response
            JwtAuthenticationResponse response = new JwtAuthenticationResponse(jwt);
            response.setUsername(authentication.getName());

            // Set roles - Fixed type inference issue
            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            String roles = userPrincipal.getAuthorities().stream()
                    .map(authority -> authority.getAuthority())
                    .collect(Collectors.joining(", "));
            response.setRoles(roles);

            return response;
        } catch (BadCredentialsException e) {
            log.error("Bad credentials for user: {}", loginRequest.getUsername());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        } catch (Exception e) {
            log.error("Authentication error: ", e);
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Authentication failed");
        }
    }

    @Autowired
    private HttpServletRequest request;

    public void logout() {
        String token = request.getHeader("Authorization");
        log.info("Logout token from header: {}", token);

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            Optional<UserSession> session = userSessionRepository.findBySessionToken(token);

            if (session.isPresent()) {
                UserSession userSession = session.get();
                log.info("Found user session: {}", userSession.getSessionId());
                userSession.setIsActive(false);
                userSession.setLogoutTime(LocalDateTime.now());
                userSessionRepository.save(userSession);
                log.info("Updated user session with logout time");
            } else {
                log.warn("No active session found for token");
            }
        } else {
            log.warn("No Authorization token found in request");
        }
        SecurityContextHolder.clearContext();
    }

    private Long getUserIdFromAuthentication(Authentication authentication) {
        if (authentication.getPrincipal() instanceof UserPrincipal) {
            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            return userPrincipal.getId();
        }
        throw new IllegalArgumentException("Authentication principal is not a UserPrincipal");
    }
}