package com.example.restapimvc.dto;

import com.example.restapimvc.common.FileMetaData;
import lombok.*;

import java.util.List;

public class InitialDataDto {


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class InitialDataRequest {
        private String os;
        private String appVersion;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class InitialDataResponse {
        private TempShell results;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class TempShell {
        private Integer notReadNotificationCount;
        private Boolean isNewest;
    }



}
