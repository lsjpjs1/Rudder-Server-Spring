package com.example.restapimvc.dto;

import com.example.restapimvc.domain.UserInfo;
import lombok.*;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class UserBlockDTO {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CreateUserBlockRequest {
        private Long blockUserInfoId;

        @Override
        public String toString() {
            return "BlockUserRequest{" +
                    "blockUserInfoId=" + blockUserInfoId +
                    '}';
        }
    }

    @Getter
    @AllArgsConstructor
    public static class CreateBlockUserResponse {
        private UserInfo userInfo;
        private UserInfo blockedUserInfo;
    }


}
