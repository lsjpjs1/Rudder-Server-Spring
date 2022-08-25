package com.example.restapimvc.notification.service;

import com.example.restapimvc.enums.NotificationType;
import com.example.restapimvc.notification.Notification;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
class SendNotificationServiceTest {

    @Autowired
    private SendNotificationService sendNotificationService;
    @Test
    void sendNotificationAsync() {
        Notification notification = Notification.builder()
                .notificationId(1l)
                .notificationBody("hello")
                .notificationTitle("hey")
                .notificationTime(new Timestamp(System.currentTimeMillis()))
                .isRead(Boolean.FALSE)
                .notificationType(NotificationType.PARTY_APPLY)
                .userInfoId(347l)
                .itemId(1l)
                .build();
        sendNotificationService.sendNotificationAsync(notification);
    }
}