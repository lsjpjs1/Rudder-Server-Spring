package com.example.restapimvc.service;

import com.example.restapimvc.domain.*;
import com.example.restapimvc.dto.MailDTO;
import com.example.restapimvc.dto.UserInfoDto;
import com.example.restapimvc.enums.MailRequestEnum;
import com.example.restapimvc.exception.CustomException;
import com.example.restapimvc.exception.ErrorCode;
import com.example.restapimvc.repository.*;
import com.example.restapimvc.security.CustomSecurityContextHolder;
import com.example.restapimvc.util.MailUtil;
import com.example.restapimvc.util.RandomNumber;
import com.example.restapimvc.util.mapper.UserInfoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserInfoService {

    private static final String EMAIL_VERIFICATION_URL = "https://rudderuni.com:8090/user-infos/";
    private final UserInfoRepository userInfoRepository;
    private final SchoolRepository schoolRepository;
    private final UserProfileRepository userProfileRepository;
    private final EmailVerificationRepository emailVerificationRepository;
    private final EmailVerificationRenewRepository emailVerificationRenewRepository;
    private final UserInfoMapper userInfoMapper;
    private final MailUtil mailUtil;
    private final SchoolService schoolService;

    @PersistenceContext
    private final EntityManager entityManager;

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

    @Transactional
    public UserInfoDto.UserInfoEntireResponse signUp(UserInfoDto.SignUpRequest signUpRequest) {
        if(userInfoRepository.findUserInfoByUserEmail(signUpRequest.getUserEmail()).isPresent()) {
            throw new CustomException(ErrorCode.EMAIL_ALREADY_EXIST);
        }
        School targetSchool = isEmailInSchools(signUpRequest.getUserEmail())
                .orElseThrow(()-> new CustomException(ErrorCode.WRONG_EMAIL_FORM));
        UserProfile userProfile = UserProfile.builder().build();
        userProfileRepository.save(userProfile);
        entityManager.refresh(userProfile);
        UserInfo userInfo = UserInfo.builder()
                .userId(signUpRequest.getUserEmail())
                .userPassword(signUpRequest.getUserPassword())
                .userEmail(signUpRequest.getUserEmail())
                .school(targetSchool)
                .userProfile(userProfile)
                .userNickname(randomNicknameGenerate())
                .userType(1)
                .build();
        userInfo.passwordEncoding();
        userInfoRepository.save(userInfo);
        String randomCode = RandomNumber.generateRandomCode(40) + System.currentTimeMillis();
        try {
            sendVerificationMail(userInfo,randomCode);
        } catch (MessagingException e) {
            throw new CustomException(ErrorCode.SEND_EMAIL_FAIL);
        }
        emailVerificationRenewRepository.save(EmailVerificationRenew.builder().emailVerificationCode(randomCode).userInfo(userInfo).build());
        return userInfoMapper.entityToUserInfoEntireResponse(userInfo);
    }

    private void sendVerificationMail(UserInfo userInfo, String randomCode) throws MessagingException {
        String verificationUrl = EMAIL_VERIFICATION_URL + userInfo.getUserInfoId() + "/verification/" + randomCode;
        mailUtil.sendMail(
                MailDTO.MailRequest.builder()
                .receiverEmail(userInfo.getUserEmail())
                .subject("Rudder verification")
                .body(verificationUrl)
                .build()
        );
    }

    private String randomNicknameGenerate(){
        return System.currentTimeMillis()+RandomNumber.generateRandomCode();
    }

    private Optional<School> isEmailInSchools(String email) {
        List<School> schools = schoolRepository.findAll();
        School targetSchool = null;
        for(School school : schools){
            if(school.validateEmailRegex(email)){
                targetSchool = school;
                break;
            }
        }
        return Optional.ofNullable(targetSchool);
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
        String verificationCode = RandomNumber.generateRandomCode();
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
            mailUtil.sendMail(MailRequestEnum.VERIFICATION_CODE.getMailRequest(userEmail,RandomNumber.generateRandomCode()));
        } catch (MessagingException e) {
            log.error(e.getMessage());
            throw new CustomException(ErrorCode.SEND_EMAIL_FAIL);
        }
    }
    public void verifyUser(Long userInfoId, String verificationCode) {
        UserInfo userInfo = userInfoRepository.findById(userInfoId).
                orElseThrow(() -> new CustomException(ErrorCode.USER_INFO_NOT_FOUND));
        EmailVerificationRenew emailVerificationRenew = emailVerificationRenewRepository.findTopByUserInfoOrderByEmailVerificationIdDesc(userInfo)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_INFO_NOT_FOUND));
        if (!emailVerificationRenew.getEmailVerificationCode().equals(verificationCode)) {
            throw new CustomException(ErrorCode.VERIFICATION_CODE_WRONG);
        }
        userInfo.verifyEmail();
        userInfoRepository.save(userInfo);
    }


}
