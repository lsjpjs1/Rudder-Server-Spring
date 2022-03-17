package com.example.restapimvc.service;

import com.example.restapimvc.domain.School;
import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.domain.UserProfile;
import com.example.restapimvc.dto.UserInfoDto;
import com.example.restapimvc.enums.MailRequestEnum;
import com.example.restapimvc.exception.CustomException;
import com.example.restapimvc.exception.ErrorCode;
import com.example.restapimvc.repository.SchoolRepository;
import com.example.restapimvc.repository.UserInfoRepository;
import com.example.restapimvc.repository.UserProfileRepository;
import com.example.restapimvc.security.CustomSecurityContextHolder;
import com.example.restapimvc.util.MailUtil;
import com.example.restapimvc.util.mapper.UserInfoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserInfoService {

    private final UserInfoRepository userInfoRepository;
    private final SchoolRepository schoolRepository;
    private final UserProfileRepository userProfileRepository;
    private final UserInfoMapper userInfoMapper;
    private final MailUtil mailUtil;

    public UserInfoDto.UserInfoResponse updateUserNickname(UserInfoDto.UpdateNicknameRequest updateNicknameRequest) {
        userInfoRepository.findUserInfoByUserNickname(updateNicknameRequest.getNickname()).ifPresent(
                p -> {
                    throw new CustomException(ErrorCode.DUPLICATE_RESOURCE);
                }
        );
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        Optional<UserInfo> targetUserInfo = userInfoRepository.findUserInfoByUserInfoId(userInfoFromToken.getUserInfoId());
        targetUserInfo.get().setUserNickname(updateNicknameRequest.getNickname());
        System.out.println(targetUserInfo.toString());
        userInfoRepository.save(targetUserInfo.get());
        return userInfoMapper.entityToUserInfoResponse(targetUserInfo.get());
    }

    public void logout() {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        UserInfo targetUserInfo = userInfoRepository.findUserInfoByUserInfoId(userInfoFromToken.getUserInfoId()).get();
        targetUserInfo.setOs(null);
        targetUserInfo.setNotificationToken(null);
        userInfoRepository.save(targetUserInfo);
    }

    public UserInfoDto.UserInfoEntireResponse signUp(UserInfoDto.SignUpRequest signUpRequest) {
        signUpRequest.passwordEncoding();
        School school = schoolRepository.findById(signUpRequest.getSchoolId()).get();
        UserProfile userProfile = UserProfile.builder()
                .profileBody(signUpRequest.getProfileBody())
                .profileImageId(signUpRequest.getUserProfileImageId())
                .build();
        userProfileRepository.save(userProfile);
        UserInfo userInfo = UserInfo.builder()
                .userId(signUpRequest.getUserId())
                .userPassword(signUpRequest.getUserPassword())
                .userEmail(signUpRequest.getUserEmail())
                .school(school)
                .userProfile(userProfile)
                .userNickname(signUpRequest.getUserNickname())
                .build();
        userInfoRepository.save(userInfo);
        return userInfoMapper.entityToUserInfoEntireResponse(userInfo);
    }

    public UserInfoDto.IsDuplicatedResponse isUserIdDuplicated(String userId) {
        Optional<UserInfo> userInfoByUserId = userInfoRepository.findUserInfoByUserId(userId);
        UserInfoDto.IsDuplicatedResponse isUserIdDuplicatedResponse = new UserInfoDto.IsDuplicatedResponse(Boolean.FALSE);
        if (userInfoByUserId.isPresent()) {
            isUserIdDuplicatedResponse.setIsDuplicated(Boolean.TRUE);
        }
        return isUserIdDuplicatedResponse;
    }

    public UserInfoDto.IsDuplicatedResponse isUserNicknameDuplicated(String nickname) {
        Optional<UserInfo> userInfoByUserNickname = userInfoRepository.findUserInfoByUserNickname(nickname);
        UserInfoDto.IsDuplicatedResponse isDuplicatedResponse = new UserInfoDto.IsDuplicatedResponse(Boolean.FALSE);
        if (userInfoByUserNickname.isPresent()) {
            isDuplicatedResponse.setIsDuplicated(Boolean.TRUE);
        }
        return isDuplicatedResponse;
    }

    public void forgotUserId(String userEmail) {
        UserInfo userInfo = userInfoRepository.findUserInfoByUserEmail(userEmail)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_EMAIL_NOT_FOUND));
        try {
            mailUtil.sendMail(MailRequestEnum.FORGOT_USER_ID.getMailRequest(userEmail, userInfo.getUserId()));
        } catch (MessagingException e) {
            log.error(e.getMessage());
            throw new CustomException(ErrorCode.SEND_EMAIL_FAIL);
        }

    }

}
