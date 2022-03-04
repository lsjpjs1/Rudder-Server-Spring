package com.example.restapimvc.service;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.domain.UserProfile;
import com.example.restapimvc.dto.UserProfileDto;
import com.example.restapimvc.exception.CustomException;
import com.example.restapimvc.exception.ErrorCode;
import com.example.restapimvc.repository.UserInfoRepository;
import com.example.restapimvc.repository.UserProfileImageRepository;
import com.example.restapimvc.repository.UserProfileRepository;
import com.example.restapimvc.security.CustomSecurityContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserProfileService {
    private final UserProfileImageRepository userProfileImageRepository;

    private final UserProfileRepository userProfileRepository;
    private final UserInfoRepository userInfoRepository;

    @Transactional
    public UserProfile updateUserProfileImage(UserProfileDto.UpdateProfileImageRequest updateProfileImageRequest) {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        UserProfile userProfile = userProfileRepository.findById(userInfoFromToken.getUserInfoId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_PROFILE_NOT_FOUND));
        userProfileImageRepository.findById(updateProfileImageRequest.getProfileImageId())
                .orElseThrow(()-> new CustomException(ErrorCode.PROFILE_IMAGE_ID_NOT_FOUND));
        userProfile.setProfileImageId(updateProfileImageRequest.getProfileImageId());
        userProfileRepository.save(userProfile);

        return userProfile;
    }
}
