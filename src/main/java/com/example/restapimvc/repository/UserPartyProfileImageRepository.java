package com.example.restapimvc.repository;

import com.example.restapimvc.domain.UserPartyProfile;
import com.example.restapimvc.domain.UserPartyProfileImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPartyProfileImageRepository extends JpaRepository<UserPartyProfileImage, Long> {
    List<UserPartyProfileImage> findByPartyProfileId(Long partyProfileId);
    void deleteByPartyProfileId(Long partyProfileId);

}
