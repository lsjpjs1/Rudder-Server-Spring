package com.example.restapimvc.job.command.dto;

import com.example.restapimvc.common.WithUserInfo;
import lombok.*;

public class JobDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class FavoriteJobRequest extends WithUserInfo.AbstractWithUserInfo {
        private Long jobId;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class FavoriteJobResponse{
        private Long jobFavoriteId;
        private Long jobId;
        private Long userInfoId;
    }
}
