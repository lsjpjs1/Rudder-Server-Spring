package com.example.restapimvc.pre.party.controller;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.dto.SchoolDTO;
import com.example.restapimvc.enums.PartyStatus;
import com.example.restapimvc.exception.ErrorResponse;
import com.example.restapimvc.pre.party.command.application.GetPartyService;
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
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags = "파티 대시보드 관련")
public class PartyDashBoardController {

    private final PartyDashBoardService partyDashBoardService;
    private final GetPartyService getPartyService;

    @Operation(summary = "파티 지원자 목록")
    @GetMapping(value = "/parties/{partyId}/applicants")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "성공")
    })
    public ResponseEntity<PartyDto.GetPartyApplicantsResponse> getPartyApplicants(@PathVariable Long partyId) {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        PartyDto.GetPartyApplicantsRequest getPartyApplicantsRequest = PartyDto.GetPartyApplicantsRequest.builder().partyId(partyId).build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(partyDashBoardService.getPartyApplicants(userInfoFromToken,getPartyApplicantsRequest));

    }

    @Operation(summary = "내가 개설한 파티 날짜 목록")
    @GetMapping(value = "/parties/my-host")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "성공")
    })
    public ResponseEntity<PartyDto.GetPartiesMyHostResponse> getPartiesMyHost() {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(getPartyService.getPartiesMyHost(userInfoFromToken));
    }

    @Operation(summary = "수락된 파티 목록")
    @GetMapping(value = "/parties/approved")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "성공")
    })
    public ResponseEntity<PartyDto.GetPartiesResponse> getApprovedParties() {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(getPartyService.getApprovedParties(userInfoFromToken));
    }


    @Operation(summary = "신청하고 대기 중인 파티 목록")
    @GetMapping(value = "/parties/pending")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "성공")
    })
    public ResponseEntity<PartyDto.GetPartiesResponse> getPendingParties() {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(getPartyService.getPendingParties(userInfoFromToken));
    }

    @Operation(summary = "파티 취소")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "성공")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/parties/{partyId}/cancel")
    public ResponseEntity cancelParty(@PathVariable Long partyId) {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        partyDashBoardService.cancelParty(userInfoFromToken,partyId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build()
                ;
    }

}
