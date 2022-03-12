package com.example.restapimvc.controller;

import com.example.restapimvc.domain.UserBlock;
import com.example.restapimvc.dto.UserBlockDTO;
import com.example.restapimvc.service.UserBlockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user-blocks")
@RequiredArgsConstructor
public class UserBlockController {
    private final UserBlockService userBlockService;

    /**
     * /users/blockUser
     * @param createUserBlockRequest Long blockUserInfoId : 차단할 userInfoId
     * @return 201, Long userBlockId,
     * UserInfo userInfo{Long userInfoId, String userId, String userNickname},
     * UserInfo blockedUserInfo{Long userInfoId, String userId, String userNickname}
     * @throws 404, USER_INFO_NOT_FOUND blockUserInfoId가 존재하지 않음
     * @throws 409, DUPLICATE_RESOURCE 이미 차단한 유저
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserBlockDTO.CreateBlockUserResponse> createUserBlock(@RequestBody UserBlockDTO.CreateUserBlockRequest createUserBlockRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userBlockService.createUserBlock(createUserBlockRequest));
    }
}
