package com.example.restapimvc.dto;

import com.example.restapimvc.domain.School;
import com.example.restapimvc.domain.UserProfile;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
    public static class OsResponse {
        private String userId;
        private String userNickname;
        private String os;
    }


}
