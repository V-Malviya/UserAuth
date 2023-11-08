package com.vaibhav.security.repository;

import com.vaibhav.security.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session,Long> {
    boolean existsSessionByToken(String token);
}
