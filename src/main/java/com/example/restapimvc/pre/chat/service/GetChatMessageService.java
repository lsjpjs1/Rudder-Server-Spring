package com.example.restapimvc.pre.chat.service;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.exception.CustomException;
import com.example.restapimvc.exception.ErrorCode;
import com.example.restapimvc.pre.chat.ChatDto;
import com.example.restapimvc.pre.chat.domain.ChatMessage;
import com.example.restapimvc.pre.chat.domain.ChatRoomMember;
import com.example.restapimvc.pre.chat.repository.ChatMessageQueryRepository;
import com.example.restapimvc.pre.chat.repository.ChatRoomMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetChatMessageService {

    private final ChatMessageQueryRepository chatMessageQueryRepository;
    private final ChatRoomMemberRepository chatRoomMemberRepository;

    @Transactional
    public ChatDto.GetChatMessagesResponse getChatMessages(UserInfo userInfo, ChatDto.GetChatMessagesRequest getChatMessagesRequest) {
        getChatMessagesRequest.setAllUserInfo(userInfo);
        ChatRoomMember chatRoomMember = chatRoomMemberRepository.findByChatRoomIdAndUserInfoId(getChatMessagesRequest.getChatRoomId(), getChatMessagesRequest.getUserInfoId())
                .orElseThrow(() -> new CustomException(ErrorCode.NO_PERMISSION));
        chatRoomMember.updateLatestReadTime();
        chatRoomMemberRepository.save(chatRoomMember);
        List<ChatDto.ChatMessageDto> chatMessages = chatMessageQueryRepository.findChatMessages(getChatMessagesRequest);
        return ChatDto.GetChatMessagesResponse.builder().chatMessages(chatMessages).build();
    }


}
