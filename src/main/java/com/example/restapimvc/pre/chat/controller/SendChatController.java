package com.example.restapimvc.pre.chat.controller;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.pre.chat.CustomMessage;
import com.example.restapimvc.pre.chat.service.SendChatMessageService;
import com.example.restapimvc.security.CustomSecurityContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RestController
@RequiredArgsConstructor
public class SendChatController {

    private final SendChatMessageService sendChatMessageService;


    @PostMapping("/send-chat")
    public ResponseEntity sendChat(@RequestBody CustomMessage customMessage) {
//        headerAccessor.getSessionAttributes().put("username", message.getSender());
        System.out.println("hit");
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        sendChatMessageService.sendChatMessage(userInfoFromToken,customMessage);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();

    }
}
