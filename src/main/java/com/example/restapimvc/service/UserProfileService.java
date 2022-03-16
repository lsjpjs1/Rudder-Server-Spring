package com.example.restapimvc.service;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.domain.UserProfile;
import com.example.restapimvc.dto.UserInfoDto;
import com.example.restapimvc.dto.UserProfileDto;
import com.example.restapimvc.exception.CustomException;
import com.example.restapimvc.exception.ErrorCode;
import com.example.restapimvc.repository.UserInfoRepository;
import com.example.restapimvc.repository.UserProfileImageRepository;
import com.example.restapimvc.repository.UserProfileRepository;
import com.example.restapimvc.security.CustomSecurityContextHolder;
import com.example.restapimvc.util.mapper.UserInfoMapper;
import com.example.restapimvc.util.mapper.UserProfileMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserProfileService {
    private final UserProfileImageRepository userProfileImageRepository;

    private final UserProfileRepository userProfileRepository;
    private final UserInfoRepository userInfoRepository;

    private final UserInfoMapper userInfoMapper;
    private final UserProfileMapper userProfileMapper;

    public UserInfoDto.UserInfoWithProfileResponse updateUserProfileImage(UserProfileDto.UpdateProfileImageRequest updateProfileImageRequest) {
        userProfileImageRepository.findById(updateProfileImageRequest.getProfileImageId())
        .orElseThrow(()-> new CustomException(ErrorCode.PROFILE_IMAGE_ID_NOT_FOUND));
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        UserInfo userInfo = userInfoRepository.findUserInfoByUserInfoId(userInfoFromToken.getUserInfoId()).get();
        userInfo.getUserProfile().setProfileImageId(updateProfileImageRequest.getProfileImageId());
        userInfoRepository.save(userInfo);
        userInfo.getUserProfile();
        return userInfoMapper.entityToUserInfoWithUserProfileResponse(userInfo);


    }

    public UserProfileDto.UserProfileResponse getUserProfile() {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        UserInfo userInfo = userInfoRepository.findUserInfoByUserInfoId(userInfoFromToken.getUserInfoId()).get();
        if (userInfo.getUserProfile()==null) {
            throw new CustomException(ErrorCode.USER_PROFILE_NOT_FOUND);
        }
        return userProfileMapper.entityToUserProfileResponse(userInfo.getUserProfile());
    }
}
