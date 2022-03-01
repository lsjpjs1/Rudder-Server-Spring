package com.example.restapimvc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
