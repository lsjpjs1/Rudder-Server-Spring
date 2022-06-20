package com.example.restapimvc.pre.chat.repository;

import com.example.restapimvc.domain.QUserInfo;
import com.example.restapimvc.pre.chat.ChatDto;
import com.example.restapimvc.pre.chat.domain.QChatMessage;
import com.example.restapimvc.pre.chat.service.GetChatMessageService;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ChatMessageQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    private final QChatMessage chatMessage = QChatMessage.chatMessage;
    private final QUserInfo userInfo = QUserInfo.userInfo;

    public List<ChatDto.ChatMessageDto> findChatMessages(ChatDto.GetChatMessagesRequest getChatMessagesRequest) {
         return jpaQueryFactory
                .select(
                        Projections.constructor(ChatDto.ChatMessageDto.class,
                                chatMessage.chatMessageId,
                                chatMessage.messageBody,
                                chatMessage.messageTime,
                                chatMessage.senderUserInfoId,
                                userInfo.userNickname,
                                new CaseBuilder()
                                        .when(chatMessage.senderUserInfoId.eq(getChatMessagesRequest.getUserInfoId())).then(Boolean.TRUE)
                                        .otherwise(Boolean.FALSE),
                                chatMessage.chatRoomId
                                )
                )
                .from(chatMessage)
                .leftJoin(userInfo).on(chatMessage.senderUserInfoId.eq(userInfo.userInfoId))
                .where(
                        chatMessage.chatRoomId.eq(getChatMessagesRequest.getChatRoomId()),
                        lessThanChatMessageId(getChatMessagesRequest.getEndChatMessageId())
                )
                .orderBy(chatMessage.chatMessageId.desc())
                .limit(20l)
                .fetch();

    }

    private BooleanExpression lessThanChatMessageId(Long chatMessageId) {
        if (chatMessageId == null) {
            return null;
        }
        return chatMessage.chatMessageId.lt(chatMessageId);
    }

}
