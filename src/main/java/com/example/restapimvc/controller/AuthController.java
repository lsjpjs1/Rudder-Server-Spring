package com.example.restapimvc.controller;

import com.example.restapimvc.dto.TokenDto;
import com.example.restapimvc.dto.UserInfoDto;
import com.example.restapimvc.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenDto> login(@RequestBody UserInfoDto.LoginRequest loginRequest){
        return ResponseEntity.ok(authService.login(loginRequest));
    }


}
