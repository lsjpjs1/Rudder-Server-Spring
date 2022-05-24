package com.example.restapimvc.repository;

import com.example.restapimvc.comment.command.domain.QComment;
import com.example.restapimvc.domain.QNotification;
import com.example.restapimvc.domain.QPostMessage;
import com.example.restapimvc.dto.NotificationDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.StringExpression;
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
                                notification.notificationTime,
                                getItemId(),
                                getItemBody(),
                                getItemTitle()
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

    private NumberExpression<Long> getItemId() {
        return new CaseBuilder()
                .when(notification.notificationType.eq(2)).then(postMessage.postMessageRoomId)
                .otherwise(comment.post.postId);
    }

    private StringExpression getItemBody() {
        return new CaseBuilder()
                .when(notification.notificationType.eq(2)).then(postMessage.postMessageBody)
                .otherwise(comment.post.postBody);
    }

    private StringExpression getItemTitle() {
        return new CaseBuilder()
                .when(notification.notificationType.eq(2)).then("New post message!")
                .otherwise("New comment!");
    }
}
