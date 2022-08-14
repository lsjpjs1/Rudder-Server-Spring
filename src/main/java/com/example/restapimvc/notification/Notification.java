package com.example.restapimvc.notification;

import com.example.restapimvc.enums.NotificationType;
import com.example.restapimvc.util.converter.NotificationTypeConverter;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table
@AllArgsConstructor
@Builder
@Setter
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    @Convert(converter = NotificationTypeConverter.class)
    private NotificationType notificationType;
    private Timestamp notificationTime;
    private Long itemId;
    private Long userInfoId;

    private String notificationTitle;
    private String notificationBody;

    private Boolean isRead;
}
