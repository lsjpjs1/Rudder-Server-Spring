package com.example.restapimvc.common;

import com.example.restapimvc.domain.UserInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class UtilTest {

    @Autowired
    private JavaMailSender sender;

    @Test
    public void objectMapperTest() {
        ObjectMapper objectMapper = new ObjectMapper();
        UserInfo userInfo = UserInfo.builder()
                .userInfoId(1L)
                .userId("123")
                .build();
        HashMap<String, Object> map = objectMapper.convertValue(userInfo, HashMap.class);

        for(String key : map.keySet()) {
            System.out.println(key + " : " + map.get(key));
        }
    }

    @Test
    public void mailTest() throws MessagingException {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setTo("lsjpjs1@naver.com");
        helper.setSubject("mail test");
        helper.setText("body");
        sender.send(message);
    }
}
