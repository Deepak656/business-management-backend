package com.example.lpgmanagement.controllers;

import com.example.lpgmanagement.models.auth.User;
import com.example.lpgmanagement.repositories.auth.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/debug")
public class DebugController {

    private final UserRepository userRepository;

    public DebugController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/admin-roles")
    public ResponseEntity<?> getAdminRoles() {
        Optional<User> adminUser = userRepository.findByUsername("deepak01962@gmail.com");
        if (adminUser.isPresent()) {
            return ResponseEntity.ok(Map.of(
                    "username", adminUser.get().getUsername(),
                    "roles", adminUser.get().getRoles()
            ));
        }
        return ResponseEntity.notFound().build();
    }
}