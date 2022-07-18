package com.example.restapimvc.pre.party.command.application;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.exception.CustomException;
import com.example.restapimvc.exception.ErrorCode;
import com.example.restapimvc.post.command.domain.FileUploadRepository;
import com.example.restapimvc.post.command.domain.Post;
import com.example.restapimvc.post.command.dto.FileDto;
import com.example.restapimvc.pre.chat.domain.ChatRoom;
import com.example.restapimvc.pre.chat.domain.ChatRoomMember;
import com.example.restapimvc.pre.chat.repository.ChatRoomMemberRepository;
import com.example.restapimvc.pre.chat.repository.ChatRoomRepository;
import com.example.restapimvc.pre.party.command.domain.Party;
import com.example.restapimvc.pre.party.command.domain.PartyRepository;
import com.example.restapimvc.pre.party.command.dto.PartyDto;
import com.example.restapimvc.util.RandomNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class CreatePartyService {

    private final Integer MIN_PARTY_MEMBER = 5;

    private final PartyRepository partyRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomMemberRepository chatRoomMemberRepository;
    private final FileUploadRepository fileUploadRepository;

    @Transactional
    public PartyDto.CreatePartyResponse createParty(UserInfo userInfo, PartyDto.CreatePartyRequest createPartyRequest) {
        createPartyRequest.setAllUserInfo(userInfo);

        if (createPartyRequest.getTotalNumberOfMember() < MIN_PARTY_MEMBER) {
            throw new CustomException(ErrorCode.PARTY_MEMBER_TOO_SMALL);
        }
        partyRepository.findByPartyHostUserInfoIdAndAndPartyTime(createPartyRequest.getUserInfoId(), createPartyRequest.getPartyTime())
                .ifPresent(party -> {
                    throw new CustomException(ErrorCode.PARTY_ALREADY_EXIST);
                });

        ChatRoom chatRoom = ChatRoom.builder().build();
        chatRoomRepository.save(chatRoom);
        ChatRoomMember chatRoomMember = ChatRoomMember.builder().chatRoomId(chatRoom.getChatRoomId()).userInfoId(createPartyRequest.getUserInfoId()).build();
        chatRoomMemberRepository.save(chatRoomMember);
        createPartyRequest.setChatRoomId(chatRoom.getChatRoomId());
        Party party = Party.from(createPartyRequest);
        party.registerHost(userInfo);
        partyRepository.save(party);
        return PartyDto.CreatePartyResponse.builder().partyId(party.getPartyId()).build();
    }

    @Transactional
    public FileDto.UploadUrlsWrapper getImageUploadUrl(UserInfo userInfo, PartyDto.PartyThumbnailUploadUrlRequest partyThumbnailUploadUrlRequest) {
        partyThumbnailUploadUrlRequest.setAllUserInfo(userInfo);
        if (partyThumbnailUploadUrlRequest.getImageMetaData() == null) {
            throw new CustomException(ErrorCode.BAD_REQUEST_CONTENT);
        }
        Party party = partyRepository.findById(partyThumbnailUploadUrlRequest.getPartyId())
                .orElseThrow(() -> new CustomException(ErrorCode.PARTY_NOT_FOUND));
        String fileName = new Date().getTime() + RandomNumber.generateRandomCode(6);
        party.setPartyThumbnailName(fileName);
        partyThumbnailUploadUrlRequest.getImageMetaData().setFileName(fileName);
        partyRepository.save(party);

        return FileDto.UploadUrlsWrapper.builder().uploadUrls(
                fileUploadRepository.getFileUploadUrls(new ArrayList(
                        Arrays.asList(partyThumbnailUploadUrlRequest.getImageMetaData())
                ))
        ).build();

    }

}
