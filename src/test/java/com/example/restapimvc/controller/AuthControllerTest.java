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
                                .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJvcyI6IkFORFJPSUQiLCJhdXRoIjoiUk9MRV9VU0VSIiwidXNlck5pY2tuYW1lIjoi7ZuIIiwidXNlckVtYWlsIjoieG9ydWRmbDc3MkBuYXZlci5jb20iLCJ1c2VySWQiOiJhYmNkIiwidXNlckluZm9JZCI6MjE4LCJub3RpZmljYXRpb25Ub2tlbiI6ImZqdlozVlFHU1BPbkp6ZzFjME1OT006QVBBOTFiR3pRWTZKdTJaeXRvZHJsemR1T2dLSTFiMy00ZDhtSmxhTkdENXk1STgwRGZYYnMxUDVRcXZmN2Zuc09Fak5GUFJsZGlkM3VsRDRiRkVUUWpDUzJ1NzBpZXZ5R0Q3Sy0xb1lmdkxmeVVfY0ZZWmlXb2tnUWZ2NVowNVBxUXluV3RFbUZLRzAifQ.bIZIXnIFs_RMq0oxT4kWbYZ-CPdWcErEtomY4GlA7TCjJ_Km5lw_cNmYabHHGHnBWIpNWZABCzQrgqmYVgeQZw")
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
//        contents.add(objectMapper.writeValueAsString(new UserInfoDto.LoginRequest("abcd","passwordwrong","123","android")));
        contents.add(objectMapper.writeValueAsString(new UserInfoDto.LoginRequest("abcd","123123123a","rightCase",null)));


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