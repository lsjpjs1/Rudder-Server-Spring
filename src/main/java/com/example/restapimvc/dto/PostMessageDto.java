package com.example.restapimvc.dto;

import com.example.restapimvc.common.WithUserInfo;
import lombok.*;

public class PostMessageDto {
    @Getter
    @AllArgsConstructor
    @Builder
    @Setter
    @ToString
    public static class SendPostMessageRequest extends WithUserInfo.AbstractWithUserInfo {
        private Long receiveUserInfoId;
        private String messageBody;
    }
}
