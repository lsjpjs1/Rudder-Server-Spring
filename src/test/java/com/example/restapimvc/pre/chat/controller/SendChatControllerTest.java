package com.example.restapimvc.pre.chat.controller;

import com.example.restapimvc.comment.command.dto.CommentDto;
import com.example.restapimvc.pre.chat.CustomMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class SendChatControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @Test
    void sendChat() throws Exception {
        String content = objectMapper.writeValueAsString(CustomMessage.builder().body("hi").sender("me").channelId(47l).build());

        mockMvc.perform(post("/send-chat")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content)
                        .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJwcm9tb3Rpb25NYWlsQWdyZWVtZW50IjpmYWxzZSwiYXV0aCI6IlJPTEVfVVNFUiIsInNjaG9vbCI6eyJzY2hvb2xJZCI6NCwic2Nob29sTmFtZSI6Im5hdmVyIiwicmVnZXgiOiJeW2EtekEtWjAtOVxcLlxcX1xcLV0rQG5hdmVyXFwuY29tJCJ9LCJ1c2VyTmlja25hbWUiOiJhZG1pbm0xIiwidXNlckVtYWlsIjoibWhwYXJrMDIyMUBuYXZlci5jb20iLCJ1c2VyVHlwZSI6MCwidXNlcklkIjoibWhwYXJrMDIyMUBuYXZlci5jb20iLCJ1c2VySW5mb0lkIjozNDksIm5vdGlmaWNhdGlvblRva2VuIjoicmlnaHRDYXNlIn0.MgDWPotPilvauQuXCuTJMKgLJNdV8gvVO1zdGB3ZHDOwdwIydR6eI7RkEQyLF2_U6Q6AxbdS8mpEVQmJaFYypw"))
                .andDo(print())
        ;
    }
}