package com.example.restapimvc.dto;

import com.example.restapimvc.common.WithUserInfo;
import com.example.restapimvc.domain.School;
import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.domain.UserProfile;
import com.example.restapimvc.security.Sha1PasswordEncoder;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

public class UserInfoDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class LoginRequest {
        private String userId;
        private String userPassword;
        private String notificationToken;
        private String os;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateNicknameRequest {
        private String nickname;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    public static class ChangePasswordRequest extends WithUserInfo.AbstractWithUserInfo {
        private String currentPassword;
        private String newPassword;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ValidateEmailRequest {
        private Long schoolId;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class SignUpRequest {
        private String userEmail;
        private String userPassword;
        private String userNickname;
        private String userProfileBody;
        private Boolean promotionMailAgreement;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserInfoResponse {
        private String userId;
        private String userNickname;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CheckVerificationCodeRequest {
        private String verificationCode;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserInfoWithProfileResponse {
        private String userId;
        private String userNickname;
        private UserProfile userProfile;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserInfoEntireResponse {

        private String userId;
        private School school;
        private String userNickname;
        private UserProfile userProfile;
        private String userEmail;
        private Long userInfoId;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class IsDuplicatedResponse {
        private Boolean isDuplicated;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OsResponse {
        private String userId;
        private String userNickname;
        private String os;
    }


}
