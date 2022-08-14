package com.example.restapimvc.service;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.dto.NotificationDto;
import com.example.restapimvc.notification.NotificationQueryRepository;
import com.example.restapimvc.notification.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationQueryRepository notificationQueryRepository;

    private final NotificationRepository notificationRepository;

    public NotificationDto.GetNotificationResponse getNotifications(UserInfo userInfo, NotificationDto.GetNotificationRequest getNotificationRequest) {
        getNotificationRequest.setAllUserInfo(userInfo);
        notificationQueryRepository.findNotificationsNotRead(getNotificationRequest).forEach(notification -> {
            notification.setIsRead(true);
            notificationRepository.save(notification);
        });
        return NotificationDto.GetNotificationResponse.builder()
                .notifications(notificationQueryRepository.findNotifications(getNotificationRequest)).build();
    }
}