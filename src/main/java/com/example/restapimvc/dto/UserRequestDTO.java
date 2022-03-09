package com.example.restapimvc.dto;

import com.example.restapimvc.domain.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class UserRequestDTO {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateUserRequestRequest {
        private String body;
    }
    @Getter
    @AllArgsConstructor
    public static class CreateUserRequestResponse {
        private long requestId;
        private long userInfoId;
        private String body;
    }

    @Getter
    @AllArgsConstructor
    public static class UserRequestResponse {
        private UserInfo userInfo;
        private String body;
    }
}
