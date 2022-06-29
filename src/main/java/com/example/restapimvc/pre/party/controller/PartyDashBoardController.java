package com.example.restapimvc.pre.party.controller;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.dto.SchoolDTO;
import com.example.restapimvc.exception.ErrorResponse;
import com.example.restapimvc.pre.party.command.application.PartyDashBoardService;
import com.example.restapimvc.pre.party.command.dto.PartyDto;
import com.example.restapimvc.security.CustomSecurityContextHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api( tags = "파티 대시보드 관련")
public class PartyDashBoardController {

    private PartyDashBoardService partyDashBoardService;

    @Operation(summary = "파티 지원자 목록")
    @GetMapping(value = "/parties/applicants")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 406, message = "1.WRONG_EMAIL_FORM(잘못된 이메일 형식)", response = ErrorResponse.class),
            @ApiResponse(code = 409, message = "1.EMAIL_ALREADY_EXIST(이미 존재하는 이메일)", response = ErrorResponse.class)
    })
    public ResponseEntity<PartyDto.GetPartyApplicantsResponse> getPartyApplicants(@ModelAttribute PartyDto.GetPartyApplicantsRequest getPartyApplicantsRequest) {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(partyDashBoardService.getPartyApplicants(userInfoFromToken,getPartyApplicantsRequest));
    }
}
