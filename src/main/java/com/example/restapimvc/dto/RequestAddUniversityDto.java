package com.example.restapimvc.dto;

import com.example.restapimvc.common.WithUserInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;


public class RequestAddUniversityDto {
    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    @Builder
    public static class RequestAddUniversityRequest {
        private String requestUniversityName;

        @ApiModelProperty(hidden = true)
        private String dummy;

    }
}
