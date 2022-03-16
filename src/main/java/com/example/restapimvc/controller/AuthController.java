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

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenDto> login(@RequestBody UserInfoDto.LoginRequest loginRequest){
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    /**
     * /signupin/validationToken
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
