package com.example.restapimvc.pushnotification;

import com.example.restapimvc.notification.pushnotification.FCMNotificationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
class FCMNotificationServiceTest {

    @Autowired
    private FCMNotificationService fcmNotificationService;
    @Test
    void sendMessage() throws JsonProcessingException {
//        fcmNotificationService.sendMessage("","aaaa","title","body");
    }
}