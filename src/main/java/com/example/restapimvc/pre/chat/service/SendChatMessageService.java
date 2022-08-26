package com.example.restapimvc.pre.chat.service;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.exception.CustomException;
import com.example.restapimvc.exception.ErrorCode;
import com.example.restapimvc.notification.SocketMessageWrapper;
import com.example.restapimvc.pre.chat.ChatDto;
import com.example.restapimvc.pre.chat.CustomMessage;
import com.example.restapimvc.pre.chat.SocketMessage;
import com.example.restapimvc.pre.chat.domain.ChatMessage;
import com.example.restapimvc.pre.chat.domain.ChatRoomMember;
import com.example.restapimvc.pre.chat.repository.ChatMessageRepository;
import com.example.restapimvc.pre.chat.repository.ChatRoomMemberRepository;
import com.example.restapimvc.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SendChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomMemberRepository chatRoomMemberRepository;
    private final SimpMessageSendingOperations messageSendingOperations;
    private final UserInfoRepository userInfoRepository;

    @Transactional
    public void sendChatMessage(UserInfo userInfo, CustomMessage message) {
        message.setSendTime(new Timestamp(System.currentTimeMillis()));
        ChatMessage chatMessage = ChatMessage.builder()
                .chatRoomId(message.getChannelId())
                .messageBody(message.getBody())
                .messageTime(message.getSendTime())
                .senderUserInfoId(userInfo.getUserInfoId())
                .build();
        chatMessageRepository.save(chatMessage);


        ChatDto.ChatMessageDto chatMessageDto = ChatDto.ChatMessageDto.builder()
                .chatMessageBody(chatMessage.getMessageBody())
                .chatMessageTime(chatMessage.getMessageTime())
                .sendUserInfoId(chatMessage.getSenderUserInfoId())
                .sendUserNickname(userInfoRepository.findById(userInfo.getUserInfoId()).get().getUserNickname())
                .chatMessageId(chatMessage.getChatMessageId())
                .isMine(false)
                .chatRoomId(chatMessage.getChatRoomId())
                .build();
        SocketMessage socketMessage = SocketMessage.from(chatMessageDto);
        SocketMessageWrapper socketMessageWrapper = SocketMessageWrapper.builder().socketMessage(socketMessage).build();

        List<ChatRoomMember> chatRoomMembers = chatRoomMemberRepository.findByChatRoomId(chatMessageDto.getChatRoomId());
        for(ChatRoomMember chatRoomMember: chatRoomMembers) {
            log.info(chatRoomMember.getUserInfoId().toString());
            messageSendingOperations.convertAndSend("/queue/user." + chatRoomMember.getUserInfoId(), socketMessageWrapper);
        }


        //알림 추가해야됨
    }
}
