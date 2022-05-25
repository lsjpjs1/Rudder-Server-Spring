package com.example.restapimvc.service;

import com.example.restapimvc.domain.PostMessage;
import com.example.restapimvc.domain.PostMessageRoom;
import com.example.restapimvc.domain.PostMessageRoomMember;
import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.dto.PostMessageDto;
import com.example.restapimvc.repository.PostMessageRepository;
import com.example.restapimvc.repository.PostMessageRoomMemberRepository;
import com.example.restapimvc.repository.PostMessageRoomRepository;
import com.example.restapimvc.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostMessageService {
    private final PostMessageRoomRepository postMessageRoomRepository;
    private final PostMessageRoomMemberRepository postMessageRoomMemberRepository;
    private final PostMessageRepository postMessageRepository;
    private final UserInfoRepository userInfoRepository;
    @Transactional
    public void sendPostMessage(UserInfo userInfo, PostMessageDto.SendPostMessageRequest sendPostMessageRequest) {
        sendPostMessageRequest.setAllUserInfo(userInfo);
        List<PostMessageRoomMember> postMessageRoomMemberList = postMessageRoomMemberRepository
                .findBySenderAndReceiver(sendPostMessageRequest.getUserInfoId(), sendPostMessageRequest.getReceiveUserInfoId());
        PostMessageRoom postMessageRoom;
        UserInfo receiveUserInfo = userInfoRepository.findUserInfoByUserInfoId(sendPostMessageRequest.getReceiveUserInfoId()).get();
        UserInfo sendUserInfo = userInfoRepository.findUserInfoByUserInfoId(sendPostMessageRequest.getUserInfoId()).get();
        if (!postMessageRoomMemberList.isEmpty()) {
            postMessageRoom = postMessageRoomRepository.findById(postMessageRoomMemberList.get(0).getPostMessageRoomId()).get();
        } else {
            postMessageRoom = postMessageRoomRepository.save(PostMessageRoom.builder().build());
            postMessageRoomMemberRepository.save(PostMessageRoomMember.builder()
                    .postMessageRoomId(postMessageRoom.getPostMessageRoomId())
                    .userInfo(receiveUserInfo)
                    .build()
            );
            postMessageRoomMemberRepository.save(PostMessageRoomMember.builder()
                    .postMessageRoomId(postMessageRoom.getPostMessageRoomId())
                    .userInfo(sendUserInfo)
                    .build()
            );
        }
        postMessageRepository.save(
                PostMessage.builder()
                        .postMessageRoomId(postMessageRoom.getPostMessageRoomId())
                        .postMessageBody(sendPostMessageRequest.getMessageBody())
                        .receiveUserInfo(receiveUserInfo)
                        .sendUserInfo(sendUserInfo)
                        .build()
        );


    }
}
