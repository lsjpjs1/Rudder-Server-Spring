package com.example.restapimvc.notification;

import com.example.restapimvc.enums.NotificationType;
import com.example.restapimvc.pre.chat.ChatDto;
import com.example.restapimvc.pre.chat.SocketMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SocketMessageWrapperTest {

    @Autowired
    private SimpMessageSendingOperations messageSendingOperations;
    @Test
    public void sendSocketMessageTest(){
//        ChatDto.ChatMessageDto chatMessageDto = ChatDto.ChatMessageDto.builder()
//                .chatMessageBody("소켓테스트")
//                .chatMessageTime(new Timestamp(System.currentTimeMillis()))
//                .sendUserInfoId(347l)
//                .sendUserNickname("성훈")
//                .chatMessageId(10l)
//                .isMine(false)
//                .chatRoomId(38l)
//                .build();
//        SocketMessage socketMessage = SocketMessage.from(chatMessageDto);
//        SocketMessageWrapper socketMessageWrapper = SocketMessageWrapper.builder().socketMessage(socketMessage).build();

        NotificationPayload notificationPayload = NotificationPayload.builder().notificationId(1l).notificationBody("알림 본문").notificationTitle("알림 제목").notificationTime(new Timestamp(System.currentTimeMillis()))
                .notificationType(NotificationType.PARTY_ACCEPTED).itemId(12l).build();
        SocketMessage socketMessage = SocketMessage.from(notificationPayload);
        SocketMessageWrapper socketMessageWrapper = SocketMessageWrapper.builder().socketMessage(socketMessage).build();

        messageSendingOperations.convertAndSend("/queue/user.347", socketMessageWrapper);
    }
}