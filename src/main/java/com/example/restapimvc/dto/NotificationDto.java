package com.example.restapimvc.dto;

import com.example.restapimvc.common.WithUserInfo;
import com.example.restapimvc.notification.NotificationPayload;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class NotificationDto {


    @Getter
    @AllArgsConstructor
    @Builder
    @Setter
    public static class GetNotificationResponse {
        private List<NotificationPayload> notifications;

    }

    @Getter
    @AllArgsConstructor
    @Builder
    @Setter
    public static class GetNotificationRequest extends WithUserInfo.AbstractWithUserInfo {
        private Long endNotificationId;
    }
}
