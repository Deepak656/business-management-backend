package com.example.lpgmanagement.security.jwt.service;

import com.example.lpgmanagement.models.auth.User;
import com.example.lpgmanagement.repositories.auth.UserRepository;
import com.example.lpgmanagement.security.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        logger.info("Loading user by username: {}", email);
        User user = userRepository.findByUsername(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        logger.info("Found user: {}, roles: {}", user.getUsername(), user.getRoles());
        return UserPrincipal.create(user);
    }

    public UserDetails loadUserById(Long userId) {
        logger.info("Loading user by ID: {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        logger.info("Found user by ID: {}, roles: {}", user.getUsername(), user.getRoles());

        return UserPrincipal.create(user);  // Use the same UserPrincipal.create method for consistency
    }
}