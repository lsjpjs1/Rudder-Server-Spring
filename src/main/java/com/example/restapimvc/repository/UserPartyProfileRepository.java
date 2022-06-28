package com.example.restapimvc.repository;

import com.example.restapimvc.domain.UserPartyProfile;
import com.example.restapimvc.domain.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPartyProfileRepository extends JpaRepository<UserPartyProfile, Long> {
}
