package com.example.restapimvc.notification;

import com.example.restapimvc.comment.command.domain.QComment;
import com.example.restapimvc.domain.QPostMessage;
import com.example.restapimvc.dto.NotificationDto;
import com.example.restapimvc.enums.NotificationType;
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

    public List<NotificationPayload> findNotifications(NotificationDto.GetNotificationRequest getNotificationRequest) {
        return jpaQueryFactory
                .select(
                        Projections.constructor(NotificationPayload.class,
                                notification.notificationId,
                                notification.notificationType,
                                notification.notificationTime,
                                notification.itemId,
                                notification.notificationBody,
                                notification.notificationTitle,
                                notification.userInfoId
                        )
                )
                .from(notification)
                .where(
                        notification.userInfoId.eq(getNotificationRequest.getUserInfoId()),
                        notificationIdLessThan(getNotificationRequest.getEndNotificationId())
                )
                .orderBy(notification.notificationId.desc())
                .limit(20)
                .fetch();



    }

    public List<Notification> findNotificationsNotRead(NotificationDto.GetNotificationRequest getNotificationRequest) {
        return jpaQueryFactory
                .select(
                       notification
                )
                .from(notification)
                .where(
                        notification.userInfoId.eq(getNotificationRequest.getUserInfoId()),
                        notification.isRead.eq(false)
                )
                .fetch();



    }

    private BooleanExpression notificationIdLessThan(Long notificationId) {
        if (notificationId == null) {
            return null;
        }
        return notification.notificationId.lt(notificationId);
    }




}
