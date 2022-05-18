package com.example.restapimvc.post.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class LookUpPostControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @Test
    void getPosts() throws Exception {
        mockMvc.perform(
                        get("/posts")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
//                                .param("endPostId","1500")
//                                .param("writerUserInfoId","218")
                                .header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJvcyI6IkFORFJPSUQiLCJhdXRoIjoiUk9MRV9VU0VSIiwic2Nob29sIjp7InNjaG9vbElkIjozLCJzY2hvb2xOYW1lIjoiTmF2ZXIgVW5pdmVyc2l0eSIsInJlZ2V4IjoiXFxiW15cXHNdK0BuYXZlclxcLmNvbVxcYiJ9LCJ1c2VyTmlja25hbWUiOiIxNjUyNDI0NDQ3NTI1NjIyMjUyIiwidXNlckVtYWlsIjoiYmExNTlzYWxAbmF2ZXIuY29tIiwidXNlclR5cGUiOjAsInVzZXJTZWxlY3RDYXRlZ29yaWVzIjpbXSwidXNlcklkIjoiYmExNTlzYWxAbmF2ZXIuY29tIiwidXNlckluZm9JZCI6MzIxLCJub3RpZmljYXRpb25Ub2tlbiI6ImM3WnhVZFBtUkdDa1VJRklzZzV6V1U6QVBBOTFiRlhIdFdvUWpnZ0thTFlQSFZURGsxOVVxTjVEc3NBV2Q0SXdKZ1R2TTk0SXRuaTNUbnJPbFR1eEplOWpZNVhES3JDVEg4NGZFeGV5OUd0dTZ1WjlJMFdPUkFZMlBfLXBZak02NWptbDczaVZKWDRDNllqX29MODA5eUN1T1VwXy1zQXhkd0EifQ.KW67iRbcXs1TzuU2r-3GpTVHyxmYjuqJgFb4uZKlTM1FgQxxvFIpfk5xOI2DEu-Y1x2sFXQSLQG2biZK4pWhgQ")
                )
                .andDo(print())
        ;
    }


    @Test
    void getPostByPostId() throws Exception {
        mockMvc.perform(
                        get("/posts/1461")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJhdXRoIjoiUk9MRV9VU0VSIiwic2Nob29sIjp7InNjaG9vbElkIjoxLCJzY2hvb2xOYW1lIjoiV2FzZWRhIFVuaXZlcnNpdHkiLCJyZWdleCI6IlxcYlteXFxzXStAd2FzZWRhXFwuanBcXGIifSwidXNlck5pY2tuYW1lIjoi7ZuIIiwidXNlckVtYWlsIjoieG9ydWRmbDc3MkBuYXZlci5jb20iLCJ1c2VySWQiOiJhYmNkIiwidXNlckluZm9JZCI6MjE4LCJub3RpZmljYXRpb25Ub2tlbiI6InJpZ2h0Q2FzZSJ9.E0CSycn5hUDS8HFg6dFHn-KQl3CDd7EoDU2gO1CqpsudtYG7daO7X8XliNPn0TNXceMPW2wG-oqbvk3wgxOEpQ")
                )
                .andDo(print())
        ;

        mockMvc.perform(
                        get("/posts/1462")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJhdXRoIjoiUk9MRV9VU0VSIiwic2Nob29sIjp7InNjaG9vbElkIjoxLCJzY2hvb2xOYW1lIjoiV2FzZWRhIFVuaXZlcnNpdHkiLCJyZWdleCI6IlxcYlteXFxzXStAd2FzZWRhXFwuanBcXGIifSwidXNlck5pY2tuYW1lIjoi7ZuIIiwidXNlckVtYWlsIjoieG9ydWRmbDc3MkBuYXZlci5jb20iLCJ1c2VySWQiOiJhYmNkIiwidXNlckluZm9JZCI6MjE4LCJub3RpZmljYXRpb25Ub2tlbiI6InJpZ2h0Q2FzZSJ9.E0CSycn5hUDS8HFg6dFHn-KQl3CDd7EoDU2gO1CqpsudtYG7daO7X8XliNPn0TNXceMPW2wG-oqbvk3wgxOEpQ")
                )
                .andDo(print())
        ;
    }
}