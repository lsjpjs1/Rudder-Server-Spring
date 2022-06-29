package com.example.restapimvc.pre.party.command.application;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.exception.CustomException;
import com.example.restapimvc.exception.ErrorCode;
import com.example.restapimvc.post.command.domain.Post;
import com.example.restapimvc.pre.chat.domain.ChatRoom;
import com.example.restapimvc.pre.chat.domain.ChatRoomMember;
import com.example.restapimvc.pre.chat.repository.ChatRoomMemberRepository;
import com.example.restapimvc.pre.chat.repository.ChatRoomRepository;
import com.example.restapimvc.pre.party.command.domain.Party;
import com.example.restapimvc.pre.party.command.domain.PartyRepository;
import com.example.restapimvc.pre.party.command.dto.PartyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreatePartyService {

    private final PartyRepository partyRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomMemberRepository chatRoomMemberRepository;

    @Transactional
    public void createParty(UserInfo userInfo,PartyDto.CreatePartyRequest createPartyRequest) {
        createPartyRequest.setAllUserInfo(userInfo);
        ChatRoom chatRoom = ChatRoom.builder().build();
        chatRoomRepository.save(chatRoom);
        ChatRoomMember chatRoomMember = ChatRoomMember.builder().chatRoomId(chatRoom.getChatRoomId()).userInfoId(createPartyRequest.getUserInfoId()).build();
        chatRoomMemberRepository.save(chatRoomMember);
        createPartyRequest.setChatRoomId(chatRoom.getChatRoomId());
        Party party = Party.from(createPartyRequest);
        party.registerHost(userInfo);
        partyRepository.save(party);
    }
}
