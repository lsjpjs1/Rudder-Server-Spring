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
    public static class InitialDataResponse {
        private Integer notReadNotificationCount;
    }
}
