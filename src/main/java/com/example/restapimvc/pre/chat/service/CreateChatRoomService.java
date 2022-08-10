package com.example.restapimvc.pre.chat.service;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.pre.chat.ChatDto;
import com.example.restapimvc.pre.chat.domain.ChatRoom;
import com.example.restapimvc.pre.chat.domain.ChatRoomMember;
import com.example.restapimvc.pre.chat.repository.ChatRoomMemberRepository;
import com.example.restapimvc.pre.chat.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomMemberRepository chatRoomMemberRepository;

    @Transactional
    public ChatDto.CreateChatRoomResponse createChatRoom(UserInfo userInfo, ChatDto.CreateChatRoomRequest createChatRoomRequest) {
        createChatRoomRequest.setAllUserInfo(userInfo);
        ChatRoom chatRoom = ChatRoom.builder().build();
        if(createChatRoomRequest.getChatRoomType()!=null && createChatRoomRequest.getChatRoomItemId()!=null) {
            chatRoom.setChatRoomType(createChatRoomRequest.getChatRoomType());
            chatRoom.setChatRoomItemId(createChatRoomRequest.getChatRoomItemId());
        }
        chatRoomRepository.save(chatRoom);
        createChatRoomRequest.getUserInfoIdList()
                .stream()
                .forEach(userInfoId->{
                    ChatRoomMember chatRoomMember1 = ChatRoomMember.builder().chatRoomId(chatRoom.getChatRoomId()).userInfoId(userInfoId).build();
                    chatRoomMemberRepository.save(chatRoomMember1);

                });

        return ChatDto.CreateChatRoomResponse.builder()
                .chatRoomId(chatRoom.getChatRoomId())
                .build();

    }
}
