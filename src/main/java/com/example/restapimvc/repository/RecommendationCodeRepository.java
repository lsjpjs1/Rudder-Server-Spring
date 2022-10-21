package com.example.restapimvc.repository;

import com.example.restapimvc.domain.EmailVerificationRenew;
import com.example.restapimvc.domain.RecommendationCode;
import com.example.restapimvc.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecommendationCodeRepository extends JpaRepository<RecommendationCode,Long> {
}
