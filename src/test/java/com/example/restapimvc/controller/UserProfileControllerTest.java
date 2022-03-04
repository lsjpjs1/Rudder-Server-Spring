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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void updateUserProfileImage() throws Exception {
        String content = objectMapper.writeValueAsString(new UserProfileDto.UpdateProfileImageRequest(2L));

        mockMvc.perform(patch("/user-profiles/profileImage")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJhdXRoIjoiUk9MRV9VU0VSIiwic2Nob29sSWQiOjEsInVzZXJJZCI6ImZmZiIsInVzZXJJbmZvSWQiOjk3fQ.NhisxLB9CXDjJt5MBiqczKi7DxoBB8l21GAnKEk_2dcc40KHka7xi_RM0gaAPzuSfDpE5Kfis_P2MPr2-S69Jg"))
                .andExpect(status().isCreated())
                .andDo(print())
        ;
    }
}