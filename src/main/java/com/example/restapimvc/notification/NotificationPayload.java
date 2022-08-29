package com.example.restapimvc.notification;

import com.example.restapimvc.enums.NotificationType;
import com.example.restapimvc.notification.SocketPayload;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.sql.Timestamp;
@Getter
@AllArgsConstructor
@Builder
@Setter
@ToString
public class NotificationPayload implements SocketPayload {

    private Long notificationId;

    private NotificationType notificationType;
    private Timestamp notificationTime;
    private Long itemId;
    private String notificationBody;
    private String notificationTitle;
    private Long userInfoId;
    @JsonIgnore
    @Override
    public String getMessageType() {
        return "NOTIFICATION";
    }

    public static NotificationPayload from(Notification notification) {
        return NotificationPayload.builder()
                .notificationId(notification.getNotificationId())
                .notificationTime(notification.getNotificationTime())
                .itemId(notification.getItemId())
                .notificationBody(notification.getNotificationBody())
                .notificationTitle(notification.getNotificationTitle())
                .userInfoId(notification.getUserInfoId())
                .notificationType(notification.getNotificationType())
                .build();
    }
}
