package com.example.restapimvc.notification.service;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.notification.Notification;
import com.example.restapimvc.notification.NotificationPayload;
import com.example.restapimvc.notification.NotificationRepository;
import com.example.restapimvc.notification.SocketMessageWrapper;
import com.example.restapimvc.notification.pushnotification.FCMNotificationService;
import com.example.restapimvc.pre.chat.SocketMessage;
import com.example.restapimvc.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class SendNotificationService {

    private final NotificationRepository notificationRepository;
    private final FCMNotificationService fcmNotificationService;
    private final UserInfoRepository userInfoRepository;
    private final SimpMessageSendingOperations messageSendingOperations;

    @Async
    @Transactional
    public void sendNotificationAsync(Notification notification){
        Notification notificationPersist = notificationRepository.save(notification);
        NotificationPayload notificationPayload = NotificationPayload.from(notificationPersist);
        UserInfo userInfo = userInfoRepository.findById(notificationPayload.getUserInfoId()).get();
        fcmNotificationService.sendMessage(userInfo.getNotificationToken(),notificationPayload,notificationPayload.getNotificationTitle(),notificationPayload.getNotificationBody());
        SocketMessageWrapper socketMessageWrapper = SocketMessageWrapper.builder()
                .socketMessage(SocketMessage.from(notificationPayload))
                .build();
        messageSendingOperations.convertAndSend("/queue/user." + notificationPayload.getUserInfoId(), socketMessageWrapper);

    }
}
