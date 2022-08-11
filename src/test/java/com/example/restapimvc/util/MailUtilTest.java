package com.example.restapimvc.util;

import com.example.restapimvc.dto.MailDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
class MailUtilTest {

    @Autowired
    private MailUtil mailUtil;
    @Test
    void sendMail() {
        MailDTO.MailRequest mailRequest = MailDTO.MailRequest.builder()
                .body("body")
                .subject("mail test")
                .receiverEmail("lsjpjs1@naver.com")
                .build();
        try {
            mailUtil.sendMail(mailRequest);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}