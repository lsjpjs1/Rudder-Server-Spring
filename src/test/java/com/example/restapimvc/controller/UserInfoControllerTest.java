package com.example.restapimvc.controller;

import com.example.restapimvc.dto.UserInfoDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

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
                        .header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJhdXRoIjoiUk9MRV9VU0VSIiwic2Nob29sIjp7InNjaG9vbElkIjoxLCJzY2hvb2xOYW1lIjoiV2FzZWRhIFVuaXZlcnNpdHkiLCJyZWdleCI6IlxcYlteXFxzXStAd2FzZWRhXFwuanBcXGIifSwidXNlck5pY2tuYW1lIjoi7ZuIIiwidXNlckVtYWlsIjoieG9ydWRmbDc3MkBuYXZlci5jb20iLCJ1c2VySWQiOiJhYmNkIiwidXNlckluZm9JZCI6MjE4LCJub3RpZmljYXRpb25Ub2tlbiI6InJpZ2h0Q2FzZSJ9.E0CSycn5hUDS8HFg6dFHn-KQl3CDd7EoDU2gO1CqpsudtYG7daO7X8XliNPn0TNXceMPW2wG-oqbvk3wgxOEpQ"))
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
                        .header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJhdXRoIjoiUk9MRV9VU0VSIiwic2Nob29sIjp7InNjaG9vbElkIjoxLCJzY2hvb2xOYW1lIjoiV2FzZWRhIFVuaXZlcnNpdHkiLCJyZWdleCI6IlxcYlteXFxzXStAd2FzZWRhXFwuanBcXGIifSwidXNlck5pY2tuYW1lIjoi7ZuIIiwidXNlckVtYWlsIjoieG9ydWRmbDc3MkBuYXZlci5jb20iLCJ1c2VySWQiOiJhYmNkIiwidXNlckluZm9JZCI6MjE4LCJub3RpZmljYXRpb25Ub2tlbiI6InJpZ2h0Q2FzZSJ9.E0CSycn5hUDS8HFg6dFHn-KQl3CDd7EoDU2gO1CqpsudtYG7daO7X8XliNPn0TNXceMPW2wG-oqbvk3wgxOEpQ"))
                .andDo(print())
        ;
    }

    @Test
    void signUp() throws Exception {
        UserInfoDto.SignUpRequest signUpRequest = UserInfoDto.SignUpRequest.builder()
                .userEmail("mhpark0220@naver.com")
                .userPassword("12345678")
                .userProfileBody("im minho")
                .userNickname("adminm")
                .promotionMailAgreement(Boolean.TRUE)
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
//
//        signUpRequest = UserInfoDto.SignUpRequest.builder()
//                .userEmail("lsjpjs1@korea.ac.kr")
//                .userPassword("123123123a")
//                .build();
//        content = objectMapper.writeValueAsString(signUpRequest);
//
//        mockMvc.perform(
//                        post("/user-infos")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .accept(MediaType.APPLICATION_JSON)
//                                .content(content)
//                )
//                .andDo(print())
//        ;
//
//        signUpRequest = UserInfoDto.SignUpRequest.builder()
//                .userEmail("lsjpjs1@seoul.ac.kr")
//                .userPassword("123123123a")
//                .build();
//        content = objectMapper.writeValueAsString(signUpRequest);
//
//        mockMvc.perform(
//                        post("/user-infos")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .accept(MediaType.APPLICATION_JSON)
//                                .content(content)
//                )
//                .andDo(print())
//        ;

    }

    @Test
    void isUserIdDuplicated() throws Exception {
        mockMvc.perform(
                        post("/user-infos/user-id/fff111/duplication-check")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
        ;
    }

    @Test
    void isUserNicknameDuplicated() throws Exception {
        mockMvc.perform(
                        post("/user-infos/user-nickname/훈111/duplication-check")
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
        String content = objectMapper.writeValueAsString(new UserInfoDto.CheckVerificationCodeRequest("377705"));
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
        content = objectMapper.writeValueAsString(new UserInfoDto.CheckVerificationCodeRequest("085582"));
        mockMvc.perform(
                        post("/user-infos/user-email/lsjpjs1@naver.com/find-user-password")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(content)
                )
                .andDo(print())
        ;
    }

    @Test
    void validateEmail() throws Exception {
        String content = objectMapper.writeValueAsString(new UserInfoDto.ValidateEmailRequest(3L));
        mockMvc.perform(
                        post("/user-infos/user-email/lsjpjs2@naver.com/validate")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(content)
                )
                .andDo(print())
        ;
    }

    @Test
    void checkVerificationCode() throws Exception {
        String content = objectMapper.writeValueAsString(new UserInfoDto.CheckVerificationCodeRequest("377705"));
        mockMvc.perform(
                        post("/user-infos/user-email/lsjpjs1@naver.com/check-verification-code")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(content)
                )
                .andDo(print())
        ;
    }

    @Test
    void verifyUser() {
    }
}