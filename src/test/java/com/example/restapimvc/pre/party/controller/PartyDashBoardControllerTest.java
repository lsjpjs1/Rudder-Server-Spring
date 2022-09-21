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
                        get("/parties/6/applicants")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJwcm9tb3Rpb25NYWlsQWdyZWVtZW50IjpmYWxzZSwiYXV0aCI6IlJPTEVfVVNFUiIsInNjaG9vbCI6eyJzY2hvb2xJZCI6NCwic2Nob29sTmFtZSI6Im5hdmVyIiwicmVnZXgiOiJtaHBhcmswMjIwQG5hdmVyLmNvbSJ9LCJ1c2VyTmlja25hbWUiOiJhZG1pbm0iLCJ1c2VyRW1haWwiOiJtaHBhcmswMjIwQG5hdmVyLmNvbSIsInVzZXJUeXBlIjowLCJ1c2VySWQiOiJtaHBhcmswMjIwQG5hdmVyLmNvbSIsInVzZXJJbmZvSWQiOjM0Nywibm90aWZpY2F0aW9uVG9rZW4iOiJyaWdodENhc2UifQ.c5ByggYQazfIK1tf0TvFf7Zg3VH4nWoQtX3o_9DV9rSa9uovGC9G4Bd9O92CagFsl10DLjLeNiV8dqbFxzMSfg")
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
                                .header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJwcm9tb3Rpb25NYWlsQWdyZWVtZW50IjpmYWxzZSwiYXV0aCI6IlJPTEVfVVNFUiIsInNjaG9vbCI6eyJzY2hvb2xJZCI6NCwic2Nob29sTmFtZSI6Im5hdmVyIiwicmVnZXgiOiJtaHBhcmswMjIwQG5hdmVyLmNvbSJ9LCJ1c2VyTmlja25hbWUiOiJhZG1pbm0iLCJ1c2VyRW1haWwiOiJtaHBhcmswMjIwQG5hdmVyLmNvbSIsInVzZXJUeXBlIjowLCJ1c2VySWQiOiJtaHBhcmswMjIwQG5hdmVyLmNvbSIsInVzZXJJbmZvSWQiOjM0Nywibm90aWZpY2F0aW9uVG9rZW4iOiJyaWdodENhc2UifQ.c5ByggYQazfIK1tf0TvFf7Zg3VH4nWoQtX3o_9DV9rSa9uovGC9G4Bd9O92CagFsl10DLjLeNiV8dqbFxzMSfg")
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
                                .header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJwcm9tb3Rpb25NYWlsQWdyZWVtZW50IjpmYWxzZSwib3MiOiJJT1MiLCJhdXRoIjoiUk9MRV9VU0VSIiwic2Nob29sIjp7InNjaG9vbElkIjo0LCJzY2hvb2xOYW1lIjoibmF2ZXIiLCJyZWdleCI6Il5bYS16QS1aMC05XFwuXFxfXFwtXStAbmF2ZXJcXC5jb20kIn0sInVzZXJOaWNrbmFtZSI6ImFkbWlubTEiLCJ1c2VyRW1haWwiOiJtaHBhcmswMjIxQG5hdmVyLmNvbSIsInVzZXJUeXBlIjowLCJ1c2VySWQiOiJtaHBhcmswMjIxQG5hdmVyLmNvbSIsInVzZXJJbmZvSWQiOjM0OSwibm90aWZpY2F0aW9uVG9rZW4iOiJBcG5Ub2tlbkZhaWwifQ.kONaIyW_LGK1L-ZK8ZptMj-z47B16Eq3_D6HKUhaMqdRIv7PyAC6tC3xEfqtoORGROq_IHkzAqepLpXmFq_7nQ")
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
                                .header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJwcm9tb3Rpb25NYWlsQWdyZWVtZW50IjpmYWxzZSwib3MiOiJJT1MiLCJhdXRoIjoiUk9MRV9VU0VSIiwic2Nob29sIjp7InNjaG9vbElkIjo0LCJzY2hvb2xOYW1lIjoibmF2ZXIiLCJyZWdleCI6Il5bYS16QS1aMC05XFwuXFxfXFwtXStAbmF2ZXJcXC5jb20kIn0sInVzZXJOaWNrbmFtZSI6ImFkbWlubTEiLCJ1c2VyRW1haWwiOiJtaHBhcmswMjIxQG5hdmVyLmNvbSIsInVzZXJUeXBlIjowLCJ1c2VySWQiOiJtaHBhcmswMjIxQG5hdmVyLmNvbSIsInVzZXJJbmZvSWQiOjM0OSwibm90aWZpY2F0aW9uVG9rZW4iOiJBcG5Ub2tlbkZhaWwifQ.kONaIyW_LGK1L-ZK8ZptMj-z47B16Eq3_D6HKUhaMqdRIv7PyAC6tC3xEfqtoORGROq_IHkzAqepLpXmFq_7nQ")
                )
                .andDo(print())
        ;
    }

    @Test
    void cancelParty() throws Exception {

        mockMvc.perform(patch("/parties/1/cancel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJwcm9tb3Rpb25NYWlsQWdyZWVtZW50IjpmYWxzZSwiYXV0aCI6IlJPTEVfVVNFUiIsInNjaG9vbCI6eyJzY2hvb2xJZCI6NCwic2Nob29sTmFtZSI6Im5hdmVyIiwicmVnZXgiOiJtaHBhcmswMjIwQG5hdmVyLmNvbSJ9LCJ1c2VyTmlja25hbWUiOiJhZG1pbm0iLCJ1c2VyRW1haWwiOiJtaHBhcmswMjIwQG5hdmVyLmNvbSIsInVzZXJUeXBlIjowLCJ1c2VySWQiOiJtaHBhcmswMjIwQG5hdmVyLmNvbSIsInVzZXJJbmZvSWQiOjM0Nywibm90aWZpY2F0aW9uVG9rZW4iOiJyaWdodENhc2UifQ.c5ByggYQazfIK1tf0TvFf7Zg3VH4nWoQtX3o_9DV9rSa9uovGC9G4Bd9O92CagFsl10DLjLeNiV8dqbFxzMSfg"))
                .andDo(print())
        ;

        mockMvc.perform(patch("/parties/4/cancel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJwcm9tb3Rpb25NYWlsQWdyZWVtZW50IjpmYWxzZSwiYXV0aCI6IlJPTEVfVVNFUiIsInNjaG9vbCI6eyJzY2hvb2xJZCI6NCwic2Nob29sTmFtZSI6Im5hdmVyIiwicmVnZXgiOiJtaHBhcmswMjIwQG5hdmVyLmNvbSJ9LCJ1c2VyTmlja25hbWUiOiJhZG1pbm0iLCJ1c2VyRW1haWwiOiJtaHBhcmswMjIwQG5hdmVyLmNvbSIsInVzZXJUeXBlIjowLCJ1c2VySWQiOiJtaHBhcmswMjIwQG5hdmVyLmNvbSIsInVzZXJJbmZvSWQiOjM0Nywibm90aWZpY2F0aW9uVG9rZW4iOiJyaWdodENhc2UifQ.c5ByggYQazfIK1tf0TvFf7Zg3VH4nWoQtX3o_9DV9rSa9uovGC9G4Bd9O92CagFsl10DLjLeNiV8dqbFxzMSfg"))
                .andDo(print())
        ;
    }

    @Test
    void approveApply() throws Exception {
        String content = objectMapper.writeValueAsString(
                PartyDto.ApproveApplyRequest.builder()
                        .partyMemberId(103l)
                        .build()
        );


        mockMvc.perform(patch("/parties/61/approve")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content)
                        .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJwcm9tb3Rpb25NYWlsQWdyZWVtZW50IjpmYWxzZSwib3MiOiJJT1MiLCJhdXRoIjoiUk9MRV9VU0VSIiwic2Nob29sIjp7InNjaG9vbElkIjo0LCJzY2hvb2xOYW1lIjoibmF2ZXIiLCJyZWdleCI6Il5bYS16QS1aMC05XFwuXFxfXFwtXStAbmF2ZXJcXC5jb20kIn0sInVzZXJOaWNrbmFtZSI6ImZpbmFsVGVzdCIsInVzZXJFbWFpbCI6ImZpbmFsVGVzdEBuYXZlci5jb20iLCJ1c2VyVHlwZSI6MCwidXNlcklkIjoiZmluYWxUZXN0QG5hdmVyLmNvbSIsInVzZXJJbmZvSWQiOjM3Niwibm90aWZpY2F0aW9uVG9rZW4iOiJBcG5Ub2tlbkZhaWwifQ.dSoTH0tVFK1dRPB9bBzSxuMyqEr_rr1ZW42D8ueo7bKcOa7sq90cPyVTFUQ-OMCp8Z0EUg1UMnXokmn7zGPzzQ"))
                .andDo(print())
        ;
    }
}