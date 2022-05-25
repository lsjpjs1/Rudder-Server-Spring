package com.example.restapimvc.controller;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.dto.NotificationDto;
import com.example.restapimvc.dto.PostMessageDto;
import com.example.restapimvc.exception.ErrorResponse;
import com.example.restapimvc.security.CustomSecurityContextHolder;
import com.example.restapimvc.service.PostMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Api( tags = "쪽지 관련")
public class PostMessageController {

    private final PostMessageService postMessageService;

    @Operation(summary = "쪽지 전송", description = "Legacy: /message/sendPostMessage")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/post-messages")
    public ResponseEntity sendPostMessage(@RequestBody PostMessageDto.SendPostMessageRequest sendPostMessageRequest) {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        postMessageService.sendPostMessage(userInfoFromToken,sendPostMessageRequest);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build()
                ;
    }

    @Operation(summary = "쪽지 읽음 처리", description = "Legacy: /message/updateIsRead")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "성공"),
            @ApiResponse(code = 404, message = "1.POST_MESSAGE_NOT_FOUND(존재하지 않는 postMessage)", response = ErrorResponse.class)
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/post-messages/{postMessageId}/read")
    public ResponseEntity readPostMessage(@PathVariable Long postMessageId) {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        postMessageService.readPostMessage(userInfoFromToken,postMessageId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build()
                ;
    }

    @Operation(summary = "내 채팅방 목록", description = "Legacy: /message/getMyMessageRooms")
    @GetMapping("/post-messages/rooms")
    public ResponseEntity<PostMessageDto.GetPostMessageRoomsResponse> getMyMessageRooms() {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(postMessageService.getMyMessageRooms(userInfoFromToken))
                ;
    }

}
