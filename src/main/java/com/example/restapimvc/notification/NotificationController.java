package com.example.restapimvc.notification;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.dto.NotificationDto;
import com.example.restapimvc.security.CustomSecurityContextHolder;
import com.example.restapimvc.service.NotificationService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api( tags = "알림 센터")
public class NotificationController {
    private final NotificationService notificationService;
    @GetMapping("/notifications")
    public ResponseEntity<NotificationDto.GetNotificationResponse> getNotifications(@ModelAttribute NotificationDto.GetNotificationRequest getNotificationRequest) {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(notificationService.getNotifications(userInfoFromToken,getNotificationRequest))
                ;
    }
}
