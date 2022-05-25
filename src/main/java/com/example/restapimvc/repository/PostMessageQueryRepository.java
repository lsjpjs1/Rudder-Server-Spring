package com.example.restapimvc.repository;

import com.example.restapimvc.domain.*;
import com.example.restapimvc.dto.PostMessageDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostMessageQueryRepository {
    @Value("${cloud-front.url.profile-image-preview}")
    private String CLOUD_FRONT_PROFILE_IMAGE_PREVIEW_URL;

    private final JPAQueryFactory jpaQueryFactory;
    private final QPostMessageRoom postMessageRoom = QPostMessageRoom.postMessageRoom;
    private final QPostMessage postMessage = QPostMessage.postMessage;
    private final QPostMessageRoomMember postMessageRoomMember = QPostMessageRoomMember.postMessageRoomMember;
    private final QUserInfo sendUserInfo = new QUserInfo("sendUserInfo");
    private final QUserInfo receiveUserInfo = new QUserInfo("receiveUserInfo");

    public List<PostMessageDto.PostMessageRoomsResponse> findMessageRooms(UserInfo userInfo) {
        return jpaQueryFactory
                .select(
                        Projections.constructor(PostMessageDto.PostMessageRoomsResponse.class,
                                postMessageRoom.postMessageRoomId,
                                postMessage.messageSendTime.max(),
                                postMessage.postMessageBody.max(),
                                new CaseBuilder()
                                        .when(receiveUserInfo.userInfoId.eq(userInfo.getUserInfoId())).then(sendUserInfo.userNickname)
                                        .otherwise(receiveUserInfo.userNickname)
                                        .max(),
                                new CaseBuilder()
                                        .when(receiveUserInfo.userInfoId.eq(userInfo.getUserInfoId())).then(sendUserInfo.userProfile.profileImageId)
                                        .otherwise(receiveUserInfo.userProfile.profileImageId)
                                        .max()
                                        .stringValue()
                                        .prepend(CLOUD_FRONT_PROFILE_IMAGE_PREVIEW_URL)
                        )
                )
                .from(postMessageRoom)
                .leftJoin(postMessageRoomMember).on(postMessageRoomMember.postMessageRoomId.eq(postMessageRoom.postMessageRoomId))
                .leftJoin(postMessage).on(postMessage.postMessageRoomId.eq(postMessageRoom.postMessageRoomId))
                .leftJoin(sendUserInfo).on(sendUserInfo.userInfoId.eq(postMessage.sendUserInfo.userInfoId))
                .leftJoin(receiveUserInfo).on(receiveUserInfo.userInfoId.eq(postMessage.receiveUserInfo.userInfoId))

                .where(
                        postMessageRoomMember.userInfo.userInfoId.eq(userInfo.getUserInfoId())
                )
                .groupBy(postMessageRoom.postMessageRoomId,postMessage.postMessageId)
                .orderBy(postMessage.postMessageId.desc())
                .fetch();

    }


    public List<PostMessageDto.PostMessageResponse> findMessagesByRoom(UserInfo userInfo, Long roomId) {
        return jpaQueryFactory
                .select(
                        Projections.constructor(PostMessageDto.PostMessageResponse.class,
                                postMessage.postMessageId,
                                postMessage.sendUserInfo.userInfoId,
                                postMessage.receiveUserInfo.userInfoId,
                                postMessage.messageSendTime,
                                postMessage.postMessageBody,
                                postMessage.isRead,
                                new CaseBuilder()
                                        .when(postMessage.sendUserInfo.userInfoId.eq(userInfo.getUserInfoId())).then(true)
                                        .otherwise(false)
                        )
                )
                .from(postMessage)
                .where(postMessage.postMessageRoomId.eq(roomId))
                .orderBy(postMessage.postMessageId.desc())
                .fetch();

    }
}
