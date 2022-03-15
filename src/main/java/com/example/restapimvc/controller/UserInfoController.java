package com.example.restapimvc.controller;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.dto.UserInfoDto;
import com.example.restapimvc.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user-infos")
@RequiredArgsConstructor
public class UserInfoController {

    private final UserInfoService userInfoService;

    /**
     * /users/updateNickname
     * @param updateNicknameRequest String nickname
     * @return 201, String userId, String userNickname
     * @throws 409, DUPLICATE_RESOURCE 이미 존재하는 닉네임
     */
    @PatchMapping(value = "/nickname",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserInfoDto.UserInfoResponse> updateUserNickname(@RequestBody UserInfoDto.UpdateNicknameRequest updateNicknameRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userInfoService.updateUserNickname(updateNicknameRequest))
                ;
    }

    /**
     * /signupin/logout
     * @return 204, 응답 body 없음
     */
    @PostMapping(value = "/logout", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity logout() {
        userInfoService.logout();
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }


    /**
     * /signupin/signupinsert
     * @param signUpRequest
     *         private String userId;
     *         private String userPassword;
     *         private String userEmail;
     *         private String recommendationCode;
     *         private Long schoolId;
     *         private String profileBody;
     *         private String userNickname;
     *         private Long userProfileImageId;
     * @return 204,
     *         String userId
     *         String userNickname
     *         String userEmail
     *         School school{Long SchoolId, String schoolName}
     *         UserProfile userProfile{Long profileId, String profileBody, Long profileImageId}
     */
    @PostMapping
    public ResponseEntity<UserInfoDto.UserInfoEntireResponse> signUp(@RequestBody UserInfoDto.SignUpRequest signUpRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userInfoService.signUp(signUpRequest));
    }


}
