package com.example.restapimvc.controller;

import com.example.restapimvc.dto.UserInfoDto;
import com.example.restapimvc.dto.UserRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserInfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void updateUserNickname() throws Exception {
        String content = objectMapper.writeValueAsString(new UserInfoDto.UpdateNicknameRequest("hell345659123212357"));

        mockMvc.perform(patch("/user-infos/nickname")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJhdXRoIjoiUk9MRV9VU0VSIiwic2Nob29sSWQiOjEsInVzZXJJZCI6ImZmZiIsInVzZXJJbmZvSWQiOjk3fQ.NhisxLB9CXDjJt5MBiqczKi7DxoBB8l21GAnKEk_2dcc40KHka7xi_RM0gaAPzuSfDpE5Kfis_P2MPr2-S69Jg"))
                .andExpect(status().isCreated())
                .andDo(print())
        ;


    }



    @Test
    void logout() throws Exception {
//        String content = objectMapper.writeValueAsString(new UserInfoDto.UpdateNicknameRequest("helloSpring123456557"));

        mockMvc.perform(post("/user-infos/logout")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJhdXRoIjoiUk9MRV9VU0VSIiwic2Nob29sSWQiOjEsInVzZXJJZCI6ImZmZiIsInVzZXJJbmZvSWQiOjk3fQ.NhisxLB9CXDjJt5MBiqczKi7DxoBB8l21GAnKEk_2dcc40KHka7xi_RM0gaAPzuSfDpE5Kfis_P2MPr2-S69Jg"))
                .andDo(print())
        ;
    }

    @Test
    void signUp() throws Exception {
        UserInfoDto.SignUpRequest signUpRequest = UserInfoDto.SignUpRequest.builder()
                .userId("signuptest3")
                .userEmail("sign2@test.com")
                .userNickname("signuptest3")
                .userProfileImageId(2l)
                .schoolId(1l)
                .userPassword("123123123a")
                .build();
        String content = objectMapper.writeValueAsString(signUpRequest);

        mockMvc.perform(
                post("/user-infos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content)
                )
                .andDo(print())
        ;
    }

    @Test
    void isUserIdDuplicated() throws Exception {
        mockMvc.perform(
                        post("/user-infos/user-id/fff2222/duplication-check")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
        ;
    }

    @Test
    void isUserNicknameDuplicated() throws Exception {
        mockMvc.perform(
                        post("/user-infos/user-nickname/훈123/duplication-check")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
        ;
    }

    @Test
    void forgotUserId() throws Exception {
        mockMvc.perform(
                        post("/user-infos/user-email/lsjpjs1213@naver.com/find-user-id")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
        ;
    }

    @Test
    void sendVerificationCode() throws Exception {
        mockMvc.perform(
                        post("/user-infos/user-email/lsjpjs1@naver.com/verify")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
        ;
    }

    @Test
    void forgotUserPassword() throws Exception {
        String content = objectMapper.writeValueAsString(new UserInfoDto.ForgotUserPasswordRequest("377705"));
        mockMvc.perform(
                        post("/user-infos/user-email/lsjpjs1@naver.com/find-user-password")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(content)
                )
                .andDo(print())
        ;
        mockMvc.perform(
                        post("/user-infos/user-email/lsjpjs12@naver.com/find-user-password")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(content)
                )
                .andDo(print())
        ;
        mockMvc.perform(
                        post("/user-infos/user-email/Jjjj/find-user-password")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(content)
                )
                .andDo(print())
        ;
        content = objectMapper.writeValueAsString(new UserInfoDto.ForgotUserPasswordRequest("085582"));
        mockMvc.perform(
                        post("/user-infos/user-email/lsjpjs1@naver.com/find-user-password")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(content)
                )
                .andDo(print())
        ;
    }
}