package com.example.restapimvc.controller;

import com.example.restapimvc.dto.TokenDto;
import com.example.restapimvc.dto.UserInfoDto;
import com.example.restapimvc.exception.ErrorResponse;
import com.example.restapimvc.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Api( tags = "로그인 관련")
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
    @Operation(summary = "로그인", description = "Legacy: /signupin/loginJWT")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "1.EMAIL_NOT_VERIFIED(인증되지 않은 이메일)\t\n2.PASSWORD_WRONG(비밀번호 틀림)", response = ErrorResponse.class),
            @ApiResponse(code = 406, message = "1.USER_ID_NOT_FOUND(존재하지 않는 userId)", response = ErrorResponse.class),
            @ApiResponse(code = 409, message = "1.BAD_REQUEST_CONTENT(userId 혹은 userPassword null인 경우)", response = ErrorResponse.class)
    })
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
    @Operation(summary = "토큰 유효성 검사", description = "Legacy: /signupin/validationToken")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "유효한 토큰")
    })
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @PostMapping(value = "/validate",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity validateToken(){
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build()
                ;
    }


}
