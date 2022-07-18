package com.example.restapimvc.pre.party.controller;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.pre.party.command.application.PartyConfigService;
import com.example.restapimvc.security.CustomSecurityContextHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api( tags = "파티 설정 관련")
public class PartyConfigController {
    private final PartyConfigService partyConfigService;


    @Operation(summary = "파티 취소")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "성공")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/parties/{partyId}/cancel")
    public ResponseEntity cancelParty(@PathVariable Long partyId) {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        partyConfigService.cancelParty(userInfoFromToken,partyId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build()
                ;
    }


    @Operation(summary = "Stop Recruiting")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "성공")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/parties/{partyId}/stop-recruit")
    public ResponseEntity stopRecruit(@PathVariable Long partyId) {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        partyConfigService.stopRecruit(userInfoFromToken,partyId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build()
                ;
    }

    @Operation(summary = "Fix the Members")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "성공")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/parties/{partyId}/stop-recruit")
    public ResponseEntity fixMembers(@PathVariable Long partyId) {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        partyConfigService.fixMembers(userInfoFromToken,partyId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build()
                ;
    }
}
