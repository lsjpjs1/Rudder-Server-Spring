package com.example.restapimvc.repository;

import com.example.restapimvc.domain.EmailVerification;
import com.example.restapimvc.domain.EmailVerificationRenew;
import com.example.restapimvc.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailVerificationRenewRepository extends JpaRepository<EmailVerificationRenew,Long> {
    Optional<EmailVerification> findTopByUserInfoOrderByEmailVerificationIdDesc(UserInfo userInfo);
}
