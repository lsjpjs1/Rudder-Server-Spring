package com.example.restapimvc.pre.chat.service;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.enums.ChatRoomType;
import com.example.restapimvc.pre.chat.ChatDto;
import com.example.restapimvc.pre.chat.domain.ChatRoom;
import com.example.restapimvc.pre.chat.domain.ChatRoomMember;
import com.example.restapimvc.pre.chat.repository.ChatRoomMemberRepository;
import com.example.restapimvc.pre.chat.repository.ChatRoomRepository;
import com.example.restapimvc.pre.party.command.domain.PartyMember;
import com.example.restapimvc.pre.party.command.domain.PartyMemberRepository;
import com.example.restapimvc.pre.party.command.domain.PartyRepository;
import com.example.restapimvc.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomMemberRepository chatRoomMemberRepository;
    private final PartyMemberRepository partyMemberRepository;
    private final PartyRepository partyRepository;
    private final UserInfoRepository userInfoRepository;

    @Transactional
    public ChatDto.CreateChatRoomResponse createChatRoom(UserInfo userInfo, ChatDto.CreateChatRoomRequest createChatRoomRequest) {
        createChatRoomRequest.setAllUserInfo(userInfo);
        ChatRoom chatRoom = ChatRoom.builder().build();
        if(createChatRoomRequest.getChatRoomType()!=null && createChatRoomRequest.getChatRoomItemId()!=null) {
            chatRoom.setChatRoomType(createChatRoomRequest.getChatRoomType());
            chatRoom.setChatRoomItemId(createChatRoomRequest.getChatRoomItemId());
        }

        if(createChatRoomRequest.getChatRoomType().equals(ChatRoomType.PARTY_ONE_TO_ONE)){
            createChatRoomRequest.getUserInfoIdList().forEach(userInfoId->{
                PartyMember partyMember = partyMemberRepository.findTopByPartyAndAndUserInfo(partyRepository.findById(createChatRoomRequest.getChatRoomItemId()).get(), userInfoRepository.findById(userInfoId).get()).get();
                partyMember.setIsChatExist(Boolean.TRUE);
                partyMemberRepository.save(partyMember);
            });
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
