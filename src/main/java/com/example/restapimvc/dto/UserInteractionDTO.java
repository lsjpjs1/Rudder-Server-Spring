package com.example.restapimvc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class UserInteractionDTO {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BlockUserRequest {
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
    public static class BlockUserResponse {
        private boolean isSuccess;
    }
}
