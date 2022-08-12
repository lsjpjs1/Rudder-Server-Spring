package com.example.restapimvc.service;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.dto.NotificationDto;
import com.example.restapimvc.notification.NotificationQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationQueryRepository notificationQueryRepository;

    public NotificationDto.GetNotificationResponse getNotifications(UserInfo userInfo, NotificationDto.GetNotificationRequest getNotificationRequest) {
        getNotificationRequest.setAllUserInfo(userInfo);
        return NotificationDto.GetNotificationResponse.builder()
                .notifications(notificationQueryRepository.findNotifications(getNotificationRequest)).build();
    }
}