package com.example.restapimvc.repository;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.domain.UserProfileImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileImageRepository extends JpaRepository<UserProfileImage, Long> {
    Optional<UserProfileImage> findUserProfileImageByUserProfileImageId(Long userProfileImageId);

}
