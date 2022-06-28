package com.example.restapimvc.repository;

import com.example.restapimvc.domain.UserPartyProfile;
import com.example.restapimvc.domain.UserPartyProfileImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPartyProfileImageRepository extends JpaRepository<UserPartyProfileImage, Long> {
}
