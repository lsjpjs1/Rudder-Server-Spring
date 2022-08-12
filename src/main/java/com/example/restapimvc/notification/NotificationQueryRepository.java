package com.example.restapimvc.notification;

import com.example.restapimvc.comment.command.domain.QComment;
import com.example.restapimvc.domain.QPostMessage;
import com.example.restapimvc.dto.NotificationDto;
import com.example.restapimvc.dto.NotificationPayload;
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
                                notification.notificationTitle
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

    private BooleanExpression notificationIdLessThan(Long notificationId) {
        if (notificationId == null) {
            return null;
        }
        return notification.notificationId.lt(notificationId);
    }

    private NumberExpression<Long> getItemId() {
        return notification.itemId;
    }

    private StringExpression getNotificationBody() {
        return new CaseBuilder()
                .when(notification.notificationType.eq(NotificationType.PARTY_APPLY)).then("누군가가 파티에 지원했어요~")
                .when(notification.notificationType.eq(NotificationType.PARTY_ACCEPTED)).then("파티 신청이 수락되었습니다")
                .when(notification.notificationType.eq(NotificationType.PARTY_RECRUIT_END_IN_24H)).then("파티 모집이 24시간 뒤에 종료됩니다")
                .when(notification.notificationType.eq(NotificationType.PARTY_ALCOHOL_PAY_REQUEST)).then("파티 모집 완료됐으니 결제하쇼")
                .otherwise("case빌더 잘 만들었는지 확인하세요~");
    }


}
