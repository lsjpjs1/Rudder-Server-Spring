package com.example.restapimvc.dto;

import com.example.restapimvc.common.FileMetaData;
import com.example.restapimvc.common.WithUserInfo;
import lombok.*;

import java.util.List;

public class PartyProfileDto {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class PartyProfileImageUploadUrlRequest {
        private List<FileMetaData> imageMetaData;

        private Long userInfoId;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class UpdatePartyProfileRequest extends WithUserInfo.AbstractWithUserInfo {
        private String profileBody;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class GetPartyProfileResponse {
        private PartyProfileResponseDto partyProfile;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class PartyProfileResponseDto {
        private Long partyProfileId;
        private String partyProfileBody;
        private List<String> partyProfileImages;
        private String schoolName;
        private String schoolImageUrl;
        private String userNickname;
    }

}
