package com.example.restapimvc.controller;

import com.example.restapimvc.dto.UserInfoDto;
import com.example.restapimvc.dto.UserProfileDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void validateToken() throws Exception {
        mockMvc.perform(
                        post("/auth/validate")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJwcm9tb3Rpb25NYWlsQWdyZWVtZW50IjpmYWxzZSwiYXV0aCI6IlJPTEVfVVNFUiIsInNjaG9vbCI6eyJzY2hvb2xJZCI6NCwic2Nob29sTmFtZSI6Im5hdmVyIiwicmVnZXgiOiJtaHBhcmswMjIwQG5hdmVyLmNvbSJ9LCJ1c2VyTmlja25hbWUiOiJhZG1pbm0iLCJ1c2VyRW1haWwiOiJtaHBhcmswMjIwQG5hdmVyLmNvbSIsInVzZXJUeXBlIjowLCJ1c2VySWQiOiJtaHBhcmswMjIwQG5hdmVyLmNvbSIsInVzZXJJbmZvSWQiOjM0Nywibm90aWZpY2F0aW9uVG9rZW4iOiJyaWdodENhc2UifQ.c5ByggYQazfIK1tf0TvFf7Zg3VH4nWoQtX3o_9DV9rSa9uovGC9G4Bd9O92CagFsl10DLjLeNiV8dqbFxzMSfg")
                )
                .andDo(print())
        ;
    }

    @Test
    void login() throws Exception {
        List<String> contents = new ArrayList<>();
//        contents.add(objectMapper.writeValueAsString(new UserInfoDto.LoginRequest(null,"idNull","123","android")));
//        contents.add(objectMapper.writeValueAsString(new UserInfoDto.LoginRequest("pwNull",null,"123","android")));
//        contents.add(objectMapper.writeValueAsString(new UserInfoDto.LoginRequest(null,null,"123","android")));
//        contents.add(objectMapper.writeValueAsString(new UserInfoDto.LoginRequest("notExistId","123123123a","123","android")));
//        contents.add(objectMapper.writeValueAsString(new UserInfoDto.LoginRequest("lsjpjs1@naver.com","123123123a","123","android")));
        contents.add(objectMapper.writeValueAsString(new UserInfoDto.LoginRequest("mhpark0220@naver.com","12345678","rightCase",null)));


        for(String content: contents){
            mockMvc.perform(
                            post("/auth")
                                    .content(content)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .accept(MediaType.APPLICATION_JSON)
                    )
                    .andDo(print())
            ;
        }

    }
}