package com.example.restapimvc.category.controller;

import com.example.restapimvc.category.command.dto.CategoryDto;
import com.example.restapimvc.post.command.dto.EditPostDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class UserSelectCategoryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @Test
    void updateUserSelectCategory() throws Exception {
        String content = objectMapper.writeValueAsString(CategoryDto.UserSelectCategoryRequest.builder().categories(Arrays.asList(10l,7l,1l)).build());
        mockMvc.perform(put("/categories/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content)
                        .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJwcm9tb3Rpb25NYWlsQWdyZWVtZW50IjpmYWxzZSwiYXV0aCI6IlJPTEVfVVNFUiIsInNjaG9vbCI6eyJzY2hvb2xJZCI6NCwic2Nob29sTmFtZSI6Im5hdmVyIiwicmVnZXgiOiJtaHBhcmswMjIwQG5hdmVyLmNvbSJ9LCJ1c2VyTmlja25hbWUiOiJhZG1pbm0iLCJ1c2VyRW1haWwiOiJtaHBhcmswMjIwQG5hdmVyLmNvbSIsInVzZXJUeXBlIjowLCJ1c2VySWQiOiJtaHBhcmswMjIwQG5hdmVyLmNvbSIsInVzZXJJbmZvSWQiOjM0Nywibm90aWZpY2F0aW9uVG9rZW4iOiJyaWdodENhc2UifQ.c5ByggYQazfIK1tf0TvFf7Zg3VH4nWoQtX3o_9DV9rSa9uovGC9G4Bd9O92CagFsl10DLjLeNiV8dqbFxzMSfg"))
                .andDo(print())
        ;
    }

    @Test
    void requestAddCategory() throws Exception {
        String content = objectMapper.writeValueAsString(CategoryDto.RequestAddCategoryRequest.builder().categoryName("cname").requestBody("body").build());
        mockMvc.perform(post("/categories/request")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content)
                        .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJwcm9tb3Rpb25NYWlsQWdyZWVtZW50IjpmYWxzZSwiYXV0aCI6IlJPTEVfVVNFUiIsInNjaG9vbCI6eyJzY2hvb2xJZCI6NCwic2Nob29sTmFtZSI6Im5hdmVyIiwicmVnZXgiOiJtaHBhcmswMjIwQG5hdmVyLmNvbSJ9LCJ1c2VyTmlja25hbWUiOiJhZG1pbm0iLCJ1c2VyRW1haWwiOiJtaHBhcmswMjIwQG5hdmVyLmNvbSIsInVzZXJUeXBlIjowLCJ1c2VySWQiOiJtaHBhcmswMjIwQG5hdmVyLmNvbSIsInVzZXJJbmZvSWQiOjM0Nywibm90aWZpY2F0aW9uVG9rZW4iOiJyaWdodENhc2UifQ.c5ByggYQazfIK1tf0TvFf7Zg3VH4nWoQtX3o_9DV9rSa9uovGC9G4Bd9O92CagFsl10DLjLeNiV8dqbFxzMSfg"))
                .andDo(print())
        ;
    }
}