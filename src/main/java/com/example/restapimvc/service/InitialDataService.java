package com.example.restapimvc.service;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.dto.InitialDataDto;
import com.example.restapimvc.dto.NoticeDTO;
import com.example.restapimvc.enums.NoticeMention;
import com.example.restapimvc.enums.UserInfoOsType;
import com.example.restapimvc.notification.Notification;
import com.example.restapimvc.notification.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InitialDataService {

    private final NotificationRepository notificationRepository;
    public InitialDataDto.InitialDataResponse getInitialData(UserInfo userInfo) {
        List<Notification> notifications = notificationRepository.findByUserInfoIdAndIsRead(userInfo.getUserInfoId(), Boolean.FALSE);
        return InitialDataDto.InitialDataResponse
                .builder()
                .notReadNotificationCount(notifications.size())
                .build();
    }
}
