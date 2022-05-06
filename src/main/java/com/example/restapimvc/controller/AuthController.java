package com.example.restapimvc.controller;

import com.example.restapimvc.dto.TokenDto;
import com.example.restapimvc.dto.UserInfoDto;
import com.example.restapimvc.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    /**
     * @param loginRequest String userId, String userPassword, String notificationToken, String os
     * @return 200, String accessToken
     * @throws 406 BAD_REQUEST_CONTENT, userId 혹은 userPassword null인 경우
     * @throws 404 USER_ID_NOT_FOUND, 존재하지 않는 userId
     * @throws 401 PASSWORD_WRONG, 비밀번호 틀림
     * @throws 401 EMAIL_NOT_VERIFIED, 인증되지 않은 이메일
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenDto> login(@RequestBody UserInfoDto.LoginRequest loginRequest){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authService.login(loginRequest));

    }

    /**
     * Legacy: /signupin/validationToken
     * @return 토큰 유효하면 204
     */
    @PostMapping(value = "/validate",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity validateToken(){
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build()
                ;
    }


}
