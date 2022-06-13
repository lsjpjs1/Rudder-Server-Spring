package com.example.restapimvc.pre.chat.repository;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.pre.chat.domain.QChatMessage;
import com.example.restapimvc.pre.chat.domain.QChatRoom;
import com.example.restapimvc.pre.chat.domain.QChatRoomMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ChatRoomQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;
    private final QChatRoomMember chatRoomMember = QChatRoomMember.chatRoomMember;
    private final QChatMessage chatMessage = QChatMessage.chatMessage;

//    public void findChatRooms(UserInfo userInfo){
//        jpaQueryFactory
//                .select()
//                .from(chatRoomMember)
//                .leftJoin(chatMessage).on(chatRoomMember.chatRoomId.eq(chatMessage.chatRoomId))
//                .where(
//                        chatRoomMember.userInfoId.eq(userInfo.getUserInfoId())
//                )
//    }
}
