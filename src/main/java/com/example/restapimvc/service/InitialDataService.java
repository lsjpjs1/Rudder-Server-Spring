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
        InitialDataDto.TempShell tempShell = InitialDataDto.TempShell.builder()
                .notReadNotificationCount(notifications.size())
                .isNewest(true)
                .build();
        return InitialDataDto.InitialDataResponse
                .builder()
                .results(tempShell)
                .build();
    }

    public InitialDataDto.InitialDataResponse getInitialDataForGuest(InitialDataDto.InitialDataRequest initialDataRequest) {
        InitialDataDto.TempShell tempShell = InitialDataDto.TempShell.builder()
                .notReadNotificationCount(0)
                .isNewest(checkNewest(initialDataRequest))
                .build();
        return InitialDataDto.InitialDataResponse
                .builder()
                .results(tempShell)
                .build();
    }

    private Boolean checkNewest(InitialDataDto.InitialDataRequest initialDataRequest){
        if(initialDataRequest.getOs()!=null){
            if(initialDataRequest.getAppVersion()!=null){
                if(initialDataRequest.getOs().equals("ios")){
                    if(initialDataRequest.getAppVersion().equals("4.2.1")||initialDataRequest.getAppVersion().equals("4.2.2")){
                        return true;
                    }
                }else{
                    if(initialDataRequest.getAppVersion().equals("")){
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
