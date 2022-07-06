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
    public ChatDto.GetChatRoomsResponse getChatRooms(UserInfo userInfo) {
        List<Tuple> tuples = chatRoomMemberRepository.findChatRooms(userInfo.getUserInfoId());
        List<ChatDto.ChatRoomDto> chatRoomDtoList = tuples.stream()
                .map((tuple) -> ChatDto.ChatRoomDto.builder()
                        .chatRoomId(tuple.get(0, Integer.class).longValue())
                        .recentMessage(tuple.get(2, String.class))
                        .recentMessageTime(tuple.get(3, Timestamp.class))
                        .notReadMessageCount(tuple.get(4, BigInteger.class).intValue())
                        .chatRoomImageUrl("https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fbq8xOw%2FbtqNHoHglcs%2FHjJpcZPEt3HilNbKB5QIqK%2Fimg.png")
                        .build()
                )
                .collect(Collectors.toList());
        return ChatDto.GetChatRoomsResponse.builder().chatRooms(chatRoomDtoList).build();
    }

    @Transactional
    public ChatDto.GetChatRoomsResponse getHostPartyOneToOneChatRooms(UserInfo userInfo, Long partyId) {
        List<Tuple> tuples = chatRoomMemberRepository.findHostPartyOneToOneChatRooms(partyId);
        List<ChatDto.ChatRoomDto> chatRoomDtoList = tuples.stream()
                .map((tuple) -> ChatDto.ChatRoomDto.builder()
                        .chatRoomId(tuple.get(0, Integer.class).longValue())
                        .recentMessage(tuple.get(2, String.class))
                        .recentMessageTime(tuple.get(3, Timestamp.class))
                        .notReadMessageCount(tuple.get(4, BigInteger.class).intValue())
                        .chatRoomImageUrl("https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fbq8xOw%2FbtqNHoHglcs%2FHjJpcZPEt3HilNbKB5QIqK%2Fimg.png")
                        .build()
                )
                .collect(Collectors.toList());
        return ChatDto.GetChatRoomsResponse.builder().chatRooms(chatRoomDtoList).build();
    }

    @Transactional
    public ChatDto.GetChatRoomsResponse getAppliedPartyOneToOneChatRooms(UserInfo userInfo) {
        List<Tuple> tuples = chatRoomMemberRepository.findAppliedPartyOneToOneChatRooms(userInfo.getUserInfoId());
        List<ChatDto.ChatRoomDto> chatRoomDtoList = tuples.stream()
                .map((tuple) -> ChatDto.ChatRoomDto.builder()
                        .chatRoomId(tuple.get(0, Integer.class).longValue())
                        .recentMessage(tuple.get(2, String.class))
                        .recentMessageTime(tuple.get(3, Timestamp.class))
                        .notReadMessageCount(tuple.get(4, BigInteger.class).intValue())
                        .chatRoomImageUrl("https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fbq8xOw%2FbtqNHoHglcs%2FHjJpcZPEt3HilNbKB5QIqK%2Fimg.png")
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
                .chatRoomImageUrl("https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fbq8xOw%2FbtqNHoHglcs%2FHjJpcZPEt3HilNbKB5QIqK%2Fimg.png")
                .build();
    }
}
