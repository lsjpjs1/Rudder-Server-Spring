package com.example.restapimvc.dto;

import com.example.restapimvc.domain.School;
import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.domain.UserProfile;
import com.example.restapimvc.security.Sha1PasswordEncoder;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class UserInfoDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
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
    @NoArgsConstructor
    @Builder
    public static class SignUpRequest {
        private String userId;
        private String userPassword;
        private String userEmail;
        private String recommendationCode;
        private Long schoolId;
        private String profileBody;
        private String userNickname;
        private Long userProfileImageId;

        public void passwordEncoding() {
            userPassword = Sha1PasswordEncoder.getInstance().encode(userPassword);
        }

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
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class IsUserIdDuplicatedResponse {
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
