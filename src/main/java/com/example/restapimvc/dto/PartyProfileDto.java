package com.example.restapimvc.dto;

import com.example.restapimvc.common.FileMetaData;
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
}
