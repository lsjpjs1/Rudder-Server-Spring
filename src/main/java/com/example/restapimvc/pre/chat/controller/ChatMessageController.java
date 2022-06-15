package com.example.restapimvc.pre.chat.controller;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.pre.chat.ChatDto;
import com.example.restapimvc.pre.chat.service.GetChatMessageService;
import com.example.restapimvc.security.CustomSecurityContextHolder;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api( tags = "채팅 메시지 관련")
public class ChatMessageController {
    private final GetChatMessageService getChatMessageService;

    @GetMapping("/chat-messages/{chatRoomId}")
    public ResponseEntity<ChatDto.GetChatMessagesResponse> getChatMessages(@PathVariable Long chatRoomId, @ModelAttribute ChatDto.GetChatMessagesRequest getChatMessagesRequest) {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        getChatMessagesRequest.setChatRoomId(chatRoomId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(getChatMessageService.getChatMessages(userInfoFromToken, getChatMessagesRequest))
                ;
    }

}
