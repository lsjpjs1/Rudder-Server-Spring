package com.example.restapimvc.pre.chat.service;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.exception.CustomException;
import com.example.restapimvc.exception.ErrorCode;
import com.example.restapimvc.pre.chat.ChatDto;
import com.example.restapimvc.pre.chat.repository.ChatRoomMemberRepository;
import com.example.restapimvc.pre.party.command.domain.Party;
import com.example.restapimvc.pre.party.command.domain.PartyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Tuple;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetChatRoomService {

    private final ChatRoomMemberRepository chatRoomMemberRepository;
    private final PartyRepository partyRepository;


    @Transactional
    public ChatDto.GetChatRoomsResponse getHostPartyOneToOneChatRooms(UserInfo userInfo, Long partyId) {
        List<Tuple> tuples = chatRoomMemberRepository.findHostPartyOneToOneChatRooms(partyId,userInfo.getUserInfoId());
        List<ChatDto.OneToOneChatRoomDto> chatRoomDtoList = tuples.stream()
                .map((tuple) -> ChatDto.OneToOneChatRoomDto.builder()
                        .chatRoomId(tuple.get(0, Integer.class).longValue())
                        .recentMessage(tuple.get(2, String.class))
                        .recentMessageTime(tuple.get(3, Timestamp.class))
                        .notReadMessageCount(tuple.get(4, BigInteger.class).intValue())
                        .chatRoomImageUrl("http://d17a6yjghl1rix.cloudfront.net/1657524178034514172")
                        .chatRoomTitle(tuple.get(5,String.class)+" + "+tuple.get(6,Integer.class))
                        .otherUserInfoId(tuple.get(7,Integer.class).longValue())
                        .build()
                )
                .collect(Collectors.toList());
        return ChatDto.GetChatRoomsResponse.builder().chatRooms(chatRoomDtoList).build();
    }

    @Transactional
    public ChatDto.GetChatRoomsResponse getAppliedPartyOneToOneChatRooms(UserInfo userInfo) {
        List<Tuple> tuples = chatRoomMemberRepository.findAppliedPartyOneToOneChatRooms(userInfo.getUserInfoId());
        List<ChatDto.OneToOneChatRoomDto> chatRoomDtoList = tuples.stream()
                .map((tuple) -> ChatDto.OneToOneChatRoomDto.builder()
                        .chatRoomId(tuple.get(0, Integer.class).longValue())
                        .recentMessage(tuple.get(2, String.class))
                        .recentMessageTime(tuple.get(3, Timestamp.class))
                        .notReadMessageCount(tuple.get(4, BigInteger.class).intValue())
                        .chatRoomImageUrl("http://d17a6yjghl1rix.cloudfront.net/1657524178034514172")
                        .chatRoomTitle(tuple.get(5,String.class))
                        .otherUserInfoId(tuple.get(6,Integer.class).longValue())
                        .build()
                )
                .collect(Collectors.toList());
        return ChatDto.GetChatRoomsResponse.builder().chatRooms(chatRoomDtoList).build();
    }

    @Transactional
    public ChatDto.ChatRoomDto getPartyGroupChatRoom(UserInfo userInfo, ChatDto.GetPartyGroupChatRoomRequest getPartyGroupChatRoomRequest) {
        getPartyGroupChatRoomRequest.setAllUserInfo(userInfo);
        Party party = partyRepository.findById(getPartyGroupChatRoomRequest.getPartyId())
                .orElseThrow(() -> new CustomException(ErrorCode.PARTY_NOT_FOUND));
        Tuple tuple = chatRoomMemberRepository.findPartyGroupChatRoom(party.getPartyChatRoomId());
        return ChatDto.ChatRoomDto.builder()
                .chatRoomId(tuple.get(0, Integer.class).longValue())
                .recentMessage(tuple.get(2, String.class))
                .recentMessageTime(tuple.get(3, Timestamp.class))
                .notReadMessageCount(tuple.get(4, BigInteger.class).intValue())
                .chatRoomImageUrl("http://d17a6yjghl1rix.cloudfront.net/1657524178034514172")
                .chatRoomTitle(party.getPartyTitle()+" "+ tuple.get(5, BigInteger.class))
                .build();
    }
}
