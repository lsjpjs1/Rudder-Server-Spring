package com.example.restapimvc.service;

import com.example.restapimvc.domain.EmailVerification;
import com.example.restapimvc.domain.School;
import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.domain.UserProfile;
import com.example.restapimvc.dto.UserInfoDto;
import com.example.restapimvc.enums.MailRequestEnum;
import com.example.restapimvc.exception.CustomException;
import com.example.restapimvc.exception.ErrorCode;
import com.example.restapimvc.repository.EmailVerificationRepository;
import com.example.restapimvc.repository.SchoolRepository;
import com.example.restapimvc.repository.UserInfoRepository;
import com.example.restapimvc.repository.UserProfileRepository;
import com.example.restapimvc.security.CustomSecurityContextHolder;
import com.example.restapimvc.util.MailUtil;
import com.example.restapimvc.util.RandomNumber;
import com.example.restapimvc.util.mapper.UserInfoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final EmailVerificationRepository emailVerificationRepository;
    private final UserInfoMapper userInfoMapper;
    private final MailUtil mailUtil;
    private final SchoolService schoolService;

    public UserInfoDto.UserInfoResponse updateUserNickname(UserInfoDto.UpdateNicknameRequest updateNicknameRequest) {
        userInfoRepository.findUserInfoByUserNickname(updateNicknameRequest.getNickname()).ifPresent(
                p -> {
                    throw new CustomException(ErrorCode.DUPLICATE_RESOURCE);
                }
        );
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        Optional<UserInfo> targetUserInfo = userInfoRepository.findUserInfoByUserInfoId(userInfoFromToken.getUserInfoId());
        targetUserInfo.get().setUserNickname(updateNicknameRequest.getNickname());
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
        userInfo.passwordEncoding();
        userInfoRepository.save(userInfo);
        return userInfoMapper.entityToUserInfoEntireResponse(userInfo);
    }

    public void userIdDuplicationCheck(String userId) {
        if(userInfoRepository.findUserInfoByUserId(userId).isPresent()) {
            throw new CustomException(ErrorCode.DUPLICATE_RESOURCE);
        }
    }

    public void userNicknameDuplicationCheck(String nickname) {
        if(userInfoRepository.findUserInfoByUserNickname(nickname).isPresent()) {
            throw new CustomException(ErrorCode.DUPLICATE_RESOURCE);
        }
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

    public void sendVerificationCode(String userEmail) {
        userInfoRepository.findUserInfoByUserEmail(userEmail)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_EMAIL_NOT_FOUND));
        String verificationCode = RandomNumber.generateVerificationCode();
        EmailVerification emailVerification = EmailVerification.builder()
                .email(userEmail)
                .verificationCode(verificationCode)
                .build();
        emailVerificationRepository.save(emailVerification);
        try {
            mailUtil.sendMail(MailRequestEnum.VERIFICATION_CODE.getMailRequest(userEmail, verificationCode));
        } catch (MessagingException e) {
            log.error(e.getMessage());
            throw new CustomException(ErrorCode.SEND_EMAIL_FAIL);
        }
    }

    public void forgotUserPassword(String userEmail,
                                   UserInfoDto.CheckVerificationCodeRequest forgotUserPasswordRequest) {
        UserInfo userInfo = userInfoRepository.findUserInfoByUserEmail(userEmail)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_EMAIL_NOT_FOUND));
        checkVerificationCode(userEmail,forgotUserPasswordRequest);
        String newPassword = userInfo.passwordReset();
        userInfoRepository.save(userInfo);
        try {
            mailUtil.sendMail(MailRequestEnum.FORGOT_USER_PASSWORD.getMailRequest(userEmail, newPassword));
        } catch (MessagingException e) {
            log.error(e.getMessage());
            throw new CustomException(ErrorCode.SEND_EMAIL_FAIL);
        }

    }

    public void checkVerificationCode(String userEmail,
                                       UserInfoDto.CheckVerificationCodeRequest forgotUserPasswordRequest) {
        UserInfo userInfo = userInfoRepository.findUserInfoByUserEmail(userEmail)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_EMAIL_NOT_FOUND));
        EmailVerification emailVerification = emailVerificationRepository.findTopByEmailOrderByVerificationIdDesc(userInfo.getUserEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.VERIFICATION_CODE_NOT_FOUND));

        if(!emailVerification.getVerificationCode().equals(forgotUserPasswordRequest.getVerificationCode())) {
            throw new CustomException(ErrorCode.VERIFICATION_CODE_WRONG);
        }

    }

    public void validateEmail(String userEmail, UserInfoDto.ValidateEmailRequest validateEmailRequest) {
        if(userInfoRepository.findUserInfoByUserEmail(userEmail).isPresent()) {
            throw new CustomException(ErrorCode.DUPLICATE_RESOURCE);
        }
        schoolService.validateEmailRegex(userEmail,validateEmailRequest);
        try {
            mailUtil.sendMail(MailRequestEnum.VERIFICATION_CODE.getMailRequest(userEmail,RandomNumber.generateVerificationCode()));
        } catch (MessagingException e) {
            log.error(e.getMessage());
            throw new CustomException(ErrorCode.SEND_EMAIL_FAIL);
        }
    }


}
