package com.example.restapimvc.dto;

import com.example.restapimvc.domain.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class NoticeDTO {

    @Getter
    @AllArgsConstructor
    public static class NoticeRequest {
        private String os;
        private String version;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    @Setter
    public static class NoticeResponse {
        private String noticeBody;
    }
}
