package com.example.lpgmanagement.repositories.auth;

import com.example.lpgmanagement.models.auth.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface UserSessionRepository extends JpaRepository<UserSession, Long> {
    Optional<UserSession> findBySessionIdAndIsActive(String sessionId, Boolean active);
    List<UserSession> findByUserIdAndIsActive(Long userId, Boolean active);
    Optional<UserSession> findBySessionId(String sessionId);
    Optional<UserSession> findBySessionToken(String sessionToken); // Corrected method name

}