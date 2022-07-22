package com.example.restapimvc.pre.party.controller;

import com.example.restapimvc.comment.command.dto.CommentDto;
import com.example.restapimvc.pre.party.command.dto.PartyDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class PartyDashBoardControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @Test
    void getPartyApplicants() throws Exception {
        mockMvc.perform(
                        get("/parties/2/applicants")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJhdXRoIjoiUk9MRV9VU0VSIiwic2Nob29sIjp7InNjaG9vbElkIjoxLCJzY2hvb2xOYW1lIjoiV2FzZWRhIFVuaXZlcnNpdHkiLCJyZWdleCI6IlxcYlteXFxzXStAd2FzZWRhXFwuanBcXGIifSwidXNlck5pY2tuYW1lIjoi7ZuIIiwidXNlckVtYWlsIjoieG9ydWRmbDc3MkBuYXZlci5jb20iLCJ1c2VySWQiOiJhYmNkIiwidXNlckluZm9JZCI6MjE4LCJub3RpZmljYXRpb25Ub2tlbiI6InJpZ2h0Q2FzZSJ9.E0CSycn5hUDS8HFg6dFHn-KQl3CDd7EoDU2gO1CqpsudtYG7daO7X8XliNPn0TNXceMPW2wG-oqbvk3wgxOEpQ")
                )
                .andDo(print())
        ;
    }

    @Test
    void getPartiesMyHost() throws Exception {
        mockMvc.perform(
                        get("/parties/my-host")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJhdXRoIjoiUk9MRV9VU0VSIiwic2Nob29sIjp7InNjaG9vbElkIjoxLCJzY2hvb2xOYW1lIjoiV2FzZWRhIFVuaXZlcnNpdHkiLCJyZWdleCI6IlxcYlteXFxzXStAd2FzZWRhXFwuanBcXGIifSwidXNlck5pY2tuYW1lIjoi7ZuIIiwidXNlckVtYWlsIjoieG9ydWRmbDc3MkBuYXZlci5jb20iLCJ1c2VySWQiOiJhYmNkIiwidXNlckluZm9JZCI6MjE4LCJub3RpZmljYXRpb25Ub2tlbiI6InJpZ2h0Q2FzZSJ9.E0CSycn5hUDS8HFg6dFHn-KQl3CDd7EoDU2gO1CqpsudtYG7daO7X8XliNPn0TNXceMPW2wG-oqbvk3wgxOEpQ")
                )
                .andDo(print())
        ;
    }

    @Test
    void getApprovedParties() throws Exception {
        mockMvc.perform(
                        get("/parties/approved")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJhdXRoIjoiUk9MRV9VU0VSIiwic2Nob29sIjp7InNjaG9vbElkIjoxLCJzY2hvb2xOYW1lIjoiV2FzZWRhIFVuaXZlcnNpdHkiLCJyZWdleCI6IlxcYlteXFxzXStAd2FzZWRhXFwuanBcXGIifSwidXNlck5pY2tuYW1lIjoi7ZuIIiwidXNlckVtYWlsIjoieG9ydWRmbDc3MkBuYXZlci5jb20iLCJ1c2VySWQiOiJhYmNkIiwidXNlckluZm9JZCI6MjE4LCJub3RpZmljYXRpb25Ub2tlbiI6InJpZ2h0Q2FzZSJ9.E0CSycn5hUDS8HFg6dFHn-KQl3CDd7EoDU2gO1CqpsudtYG7daO7X8XliNPn0TNXceMPW2wG-oqbvk3wgxOEpQ")
                )
                .andDo(print())
        ;
    }

    @Test
    void getPendingParties() throws Exception {
        mockMvc.perform(
                        get("/parties/pending")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJhdXRoIjoiUk9MRV9VU0VSIiwic2Nob29sIjp7InNjaG9vbElkIjoxLCJzY2hvb2xOYW1lIjoiV2FzZWRhIFVuaXZlcnNpdHkiLCJyZWdleCI6IlxcYlteXFxzXStAd2FzZWRhXFwuanBcXGIifSwidXNlck5pY2tuYW1lIjoi7ZuIIiwidXNlckVtYWlsIjoieG9ydWRmbDc3MkBuYXZlci5jb20iLCJ1c2VySWQiOiJhYmNkIiwidXNlckluZm9JZCI6MjE4LCJub3RpZmljYXRpb25Ub2tlbiI6InJpZ2h0Q2FzZSJ9.E0CSycn5hUDS8HFg6dFHn-KQl3CDd7EoDU2gO1CqpsudtYG7daO7X8XliNPn0TNXceMPW2wG-oqbvk3wgxOEpQ")
                )
                .andDo(print())
        ;
    }

    @Test
    void cancelParty() throws Exception {

        mockMvc.perform(patch("/parties/1/cancel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJhdXRoIjoiUk9MRV9VU0VSIiwic2Nob29sIjp7InNjaG9vbElkIjoxLCJzY2hvb2xOYW1lIjoiV2FzZWRhIFVuaXZlcnNpdHkiLCJyZWdleCI6IlxcYlteXFxzXStAd2FzZWRhXFwuanBcXGIifSwidXNlck5pY2tuYW1lIjoi7ZuIIiwidXNlckVtYWlsIjoieG9ydWRmbDc3MkBuYXZlci5jb20iLCJ1c2VySWQiOiJhYmNkIiwidXNlckluZm9JZCI6MjE4LCJub3RpZmljYXRpb25Ub2tlbiI6InJpZ2h0Q2FzZSJ9.E0CSycn5hUDS8HFg6dFHn-KQl3CDd7EoDU2gO1CqpsudtYG7daO7X8XliNPn0TNXceMPW2wG-oqbvk3wgxOEpQ"))
                .andDo(print())
        ;

        mockMvc.perform(patch("/parties/4/cancel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJhdXRoIjoiUk9MRV9VU0VSIiwic2Nob29sIjp7InNjaG9vbElkIjoxLCJzY2hvb2xOYW1lIjoiV2FzZWRhIFVuaXZlcnNpdHkiLCJyZWdleCI6IlxcYlteXFxzXStAd2FzZWRhXFwuanBcXGIifSwidXNlck5pY2tuYW1lIjoi7ZuIIiwidXNlckVtYWlsIjoieG9ydWRmbDc3MkBuYXZlci5jb20iLCJ1c2VySWQiOiJhYmNkIiwidXNlckluZm9JZCI6MjE4LCJub3RpZmljYXRpb25Ub2tlbiI6InJpZ2h0Q2FzZSJ9.E0CSycn5hUDS8HFg6dFHn-KQl3CDd7EoDU2gO1CqpsudtYG7daO7X8XliNPn0TNXceMPW2wG-oqbvk3wgxOEpQ"))
                .andDo(print())
        ;
    }

    @Test
    void approveApply() throws Exception {
        String content = objectMapper.writeValueAsString(
                PartyDto.ApproveApplyRequest.builder()
                        .partyMemberId(10l)
                        .build()
        );


        mockMvc.perform(patch("/parties/2/approve")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content)
                        .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJhdXRoIjoiUk9MRV9VU0VSIiwic2Nob29sIjp7InNjaG9vbElkIjoxLCJzY2hvb2xOYW1lIjoiV2FzZWRhIFVuaXZlcnNpdHkiLCJyZWdleCI6IlxcYlteXFxzXStAd2FzZWRhXFwuanBcXGIifSwidXNlck5pY2tuYW1lIjoi7ZuIIiwidXNlckVtYWlsIjoieG9ydWRmbDc3MkBuYXZlci5jb20iLCJ1c2VySWQiOiJhYmNkIiwidXNlckluZm9JZCI6MjE4LCJub3RpZmljYXRpb25Ub2tlbiI6InJpZ2h0Q2FzZSJ9.E0CSycn5hUDS8HFg6dFHn-KQl3CDd7EoDU2gO1CqpsudtYG7daO7X8XliNPn0TNXceMPW2wG-oqbvk3wgxOEpQ"))
                .andDo(print())
        ;
    }
}