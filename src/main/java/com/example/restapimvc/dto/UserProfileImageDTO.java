package com.example.restapimvc.dto;

import com.example.restapimvc.domain.UserProfileImage;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;


public class UserProfileImageDTO {



    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class UserProfileImageWithURL {
        private Long userProfileImageId;
        private String highQualityUrl;
        private String previewUrl;


    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class UserProfileImageResponse {
        private List<UserProfileImageWithURL> userProfileImages;
    }

}
