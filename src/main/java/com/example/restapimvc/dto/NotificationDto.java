package com.example.restapimvc.dto;

import com.example.restapimvc.common.WithUserInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

public class NotificationDto {
    @Getter
    @AllArgsConstructor
    @Builder
    @Setter
    public static class NotificationResponse {
        private Long notificationId;
        private Integer notificationType;
        private Long postId;
        private String commentBody;
        private Timestamp postTime;
        private Long postMessageRoomId;
        private String postMessageBody;
        private Timestamp messageSendTime;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    @Setter
    public static class GetNotificationResponse {
        private List<NotificationResponse> notifications;

    }

    @Getter
    @AllArgsConstructor
    @Builder
    @Setter
    public static class GetNotificationRequest extends WithUserInfo.AbstractWithUserInfo {
        private Long endNotificationId;
    }
}
