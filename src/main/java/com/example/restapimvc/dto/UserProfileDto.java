package com.example.restapimvc.dto;

import lombok.*;

import javax.persistence.Column;

public class UserProfileDto {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateProfileImageRequest {
        private Long profileImageId;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class UserProfileResponse {
        private Long profileId;

        private String profileBody;

        private Long profileImageId;

        private String profileImageUrl;
    }

}
