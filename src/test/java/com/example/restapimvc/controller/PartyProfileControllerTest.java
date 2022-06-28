package com.example.restapimvc.controller;

import com.example.restapimvc.common.FileMetaData;
import com.example.restapimvc.dto.PartyProfileDto;
import com.example.restapimvc.post.command.dto.WritePostDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class PartyProfileControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @Test
    void getS3SignedUrl() throws Exception {
        List<FileMetaData> fileMetaData = Arrays.asList(
                FileMetaData.builder().fileName("one").contentType("image/jpeg").build(),
                FileMetaData.builder().fileName("two").contentType("image/png").build()
        );

        String content = objectMapper.writeValueAsString(PartyProfileDto.PartyProfileImageUploadUrlRequest.builder().userInfoId(327l).imageMetaData(fileMetaData).build());
        System.out.println(content);
        mockMvc.perform(post("/party-profile-image/image-upload-url/generate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content)
                )
                .andDo(print())
        ;

    }
}