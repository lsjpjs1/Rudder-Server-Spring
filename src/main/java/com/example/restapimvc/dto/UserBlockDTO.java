package com.example.restapimvc.dto;

import lombok.*;

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


}
