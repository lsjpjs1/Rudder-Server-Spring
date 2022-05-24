package com.example.restapimvc.repository;

import com.example.restapimvc.comment.command.domain.QComment;
import com.example.restapimvc.comment.command.dto.CommentDto;
import com.example.restapimvc.domain.QNotification;
import com.example.restapimvc.domain.QPostMessage;
import com.example.restapimvc.dto.NotificationDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class NotificationQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final QComment comment = QComment.comment;
    private final QNotification notification = QNotification.notification;
    private final QPostMessage postMessage = QPostMessage.postMessage;

    public List<NotificationDto.NotificationResponse> findNotifications(NotificationDto.GetNotificationRequest getNotificationRequest) {
        return jpaQueryFactory
                .select(
                        Projections.constructor(NotificationDto.NotificationResponse.class,
                                notification.notificationId,
                                notification.notificationType,
                                comment.post.postId,
                                comment.commentBody,
                                comment.post.postMetaData.postTime,
                                postMessage.postMessageRoomId,
                                postMessage.postMessageBody,
                                postMessage.messageSendTime
                                )
                )
                .from(notification)
                .leftJoin(comment).on(comment.commentId.eq(notification.commentId))
                .leftJoin(postMessage).on(postMessage.postMessageId.eq(notification.postMessageId))
                .where(
                        notification.userInfoId.eq(getNotificationRequest.getUserInfoId()),
                        notificationIdLessThan(getNotificationRequest.getEndNotificationId())
                )
                .orderBy(notification.notificationId.desc())
                .limit(20)
                .fetch();



    }

    private BooleanExpression notificationIdLessThan(Long notificationId) {
        if (notificationId == null) {
            return null;
        }
        return notification.notificationId.lt(notificationId);
    }
}
