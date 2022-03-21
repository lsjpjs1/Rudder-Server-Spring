package com.example.restapimvc.repository;

import com.example.restapimvc.domain.EmailVerification;
import com.example.restapimvc.domain.School;
import com.example.restapimvc.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailVerificationRepository extends JpaRepository<EmailVerification,Long> {
    Optional<EmailVerification> findTopByEmailOrderByVerificationIdDesc(String Email);
}
