package com.example.restapimvc.controller;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.dto.UserInfoDto;
import com.example.restapimvc.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user-infos")
@RequiredArgsConstructor
public class UserInfoController {

    private final UserInfoService userInfoService;

    /**
     * /users/updateNickname
     * @param updateNicknameRequest String nickname
     * @return 201, UserInfo
     */
    @PatchMapping(value = "/nickname",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserInfo> updateUserNickname(@RequestBody UserInfoDto.UpdateNicknameRequest updateNicknameRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userInfoService.updateUserNickname(updateNicknameRequest))
                ;
    }
}
