package com.example.restapimvc.controller;

import com.example.restapimvc.domain.UserRequest;
import com.example.restapimvc.dto.UserRequestDTO;
import com.example.restapimvc.service.UserRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user-requests")
@RequiredArgsConstructor
public class UserRequestController {

    private final UserRequestService userRequestService;

    /**
     * Legacy: /users/addUserRequest
     * @param createUserRequestRequest String body : 유저의 요청 내용(고객의 소리)
     * @return 201, String body,
     * UserInfo userInfo{Long userInfoId, String userId, String userNickname}
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserRequestDTO.UserRequestResponse> createUserRequest(@RequestBody UserRequestDTO.CreateUserRequestRequest createUserRequestRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userRequestService.createUserRequestResponse(createUserRequestRequest));
    }

}
