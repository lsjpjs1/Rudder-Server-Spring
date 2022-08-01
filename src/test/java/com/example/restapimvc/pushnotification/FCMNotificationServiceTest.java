package com.example.restapimvc.pushnotification;

import com.example.restapimvc.dto.NotificationPayload;
import com.example.restapimvc.enums.NotificationType;
import com.example.restapimvc.pre.SocketPayload;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
class FCMNotificationServiceTest {

    @Autowired
    private FCMNotificationService fcmNotificationService;
    @Test
    void sendMessage() throws JsonProcessingException {
        fcmNotificationService.sendMessage("","aaaa","title","body");
    }
}