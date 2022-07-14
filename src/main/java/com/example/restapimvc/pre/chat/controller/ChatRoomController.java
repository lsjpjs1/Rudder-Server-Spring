package com.example.restapimvc.pre.chat.controller;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.pre.chat.ChatDto;
import com.example.restapimvc.pre.chat.service.GetChatRoomService;
import com.example.restapimvc.security.CustomSecurityContextHolder;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RestController
@RequiredArgsConstructor
@Api( tags = "채팅방 관련")
public class ChatRoomController {

    private final GetChatRoomService getChatRoomService;


    @GetMapping("/chat-rooms/party-group/{partyId}")
    public ResponseEntity<ChatDto.ChatRoomDto> getPartyGroupChatRoom(@PathVariable Long partyId) {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        ChatDto.GetPartyGroupChatRoomRequest getPartyGroupChatRoomRequest = ChatDto.GetPartyGroupChatRoomRequest.builder().partyId(partyId).build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(getChatRoomService.getPartyGroupChatRoom(userInfoFromToken,getPartyGroupChatRoomRequest))
                ;
    }

    @GetMapping("/chat-rooms/party-one-to-one/{partyId}")
    public ResponseEntity<ChatDto.GetChatRoomsResponse> getHostPartyOneToOneChatRooms(@PathVariable Long partyId) {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(getChatRoomService.getHostPartyOneToOneChatRooms(userInfoFromToken,partyId))
                ;
    }

    @GetMapping("/chat-rooms/party-one-to-one")
    public ResponseEntity<ChatDto.GetChatRoomsResponse> getAppliedPartyOneToOneChatRooms() {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(getChatRoomService.getAppliedPartyOneToOneChatRooms(userInfoFromToken))
                ;
    }



}
