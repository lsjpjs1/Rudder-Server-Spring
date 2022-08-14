package com.example.restapimvc.notification;

import com.example.restapimvc.enums.NotificationType;
import com.example.restapimvc.notification.SocketPayload;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
@Getter
@AllArgsConstructor
@Builder
@Setter
public class NotificationPayload implements SocketPayload {

    private Long notificationId;

    private NotificationType notificationType;
    private Timestamp notificationTime;
    private Long itemId;
    private String notificationBody;
    private String notificationTitle;
    @JsonIgnore
    @Override
    public String getMessageType() {
        return "NOTIFICATION";
    }
}
