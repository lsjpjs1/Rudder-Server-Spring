package com.example.restapimvc.pre.chat.controller;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.pre.chat.ChatDto;
import com.example.restapimvc.pre.chat.service.CreateChatRoomService;
import com.example.restapimvc.security.CustomSecurityContextHolder;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api( tags = "채팅방 생성 관련")
public class CreateChatRoomController {

    private final CreateChatRoomService createChatRoomService;

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(value = "/chat-rooms")
    public ResponseEntity createChatRoom(@RequestBody ChatDto.CreateChatRoomRequest createChatRoomRequest) {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        createChatRoomService.createChatRoom(userInfoFromToken,createChatRoomRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }
}
