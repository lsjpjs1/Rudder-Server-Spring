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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
        String content = objectMapper.writeValueAsString(new UserProfileDto.UpdateProfileImageRequest(6L));

        mockMvc.perform(patch("/user-profiles/profileImage")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJhdXRoIjoiUk9MRV9VU0VSIiwidXNlck5pY2tuYW1lIjoi7ZuIIiwidXNlcklkIjoiYWJjZCIsInVzZXJJbmZvSWQiOjIxOH0.yHc24cssGa9BNE_Xfhw2CNKrHacQ9amuxEBdXGbJPPp7avUFRnGldj--uucsZUMji38rS74J56Wzv8aRUv7fMA"))
                .andExpect(status().isCreated())
                .andDo(print())
        ;
    }

    @Test
    void getUserProfile() throws Exception {
        mockMvc.perform(get("/user-profiles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJvcyI6IkFORFJPSUQiLCJhdXRoIjoiUk9MRV9VU0VSIiwidXNlck5pY2tuYW1lIjoi7ZuIIiwidXNlckVtYWlsIjoieG9ydWRmbDc3MkBuYXZlci5jb20iLCJ1c2VySWQiOiJhYmNkIiwidXNlckluZm9JZCI6MjE4LCJub3RpZmljYXRpb25Ub2tlbiI6ImZqdlozVlFHU1BPbkp6ZzFjME1OT006QVBBOTFiR3pRWTZKdTJaeXRvZHJsemR1T2dLSTFiMy00ZDhtSmxhTkdENXk1STgwRGZYYnMxUDVRcXZmN2Zuc09Fak5GUFJsZGlkM3VsRDRiRkVUUWpDUzJ1NzBpZXZ5R0Q3Sy0xb1lmdkxmeVVfY0ZZWmlXb2tnUWZ2NVowNVBxUXluV3RFbUZLRzAifQ.bIZIXnIFs_RMq0oxT4kWbYZ-CPdWcErEtomY4GlA7TCjJ_Km5lw_cNmYabHHGHnBWIpNWZABCzQrgqmYVgeQZw"))
                .andDo(print())
        ;
    }
}