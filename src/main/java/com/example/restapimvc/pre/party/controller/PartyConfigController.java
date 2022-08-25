package com.example.restapimvc.pre.party.controller;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.exception.ErrorResponse;
import com.example.restapimvc.pre.party.command.application.PartyConfigService;
import com.example.restapimvc.pre.party.command.dto.PartyDto;
import com.example.restapimvc.security.CustomSecurityContextHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Api( tags = "파티 설정 관련")
public class PartyConfigController {
    private final PartyConfigService partyConfigService;


    @Operation(summary = "파티 취소")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "성공"),
            @ApiResponse(code = 404, message = "1.PARTY_NOT_FOUND(존재하지 않는 파티)", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "1.NO_PERMISSION(파티장이 아님)", response = ErrorResponse.class),
            @ApiResponse(code = 406, message = "1.PARTY_CANCEL_NOT_ALLOW(파티원이 존재해서 파티 삭제 못함)", response = ErrorResponse.class),
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
            @ApiResponse(code = 204, message = "성공"),
            @ApiResponse(code = 404, message = "1.PARTY_NOT_FOUND(존재하지 않는 파티)", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "1.NO_PERMISSION(파티장이 아님)", response = ErrorResponse.class)
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
            @ApiResponse(code = 204, message = "성공"),
            @ApiResponse(code = 404, message = "1.PARTY_NOT_FOUND(존재하지 않는 파티)", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "1.NO_PERMISSION(파티장이 아님)", response = ErrorResponse.class),
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/parties/{partyId}/fix-members")
    public ResponseEntity fixMembers(@PathVariable Long partyId) {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        partyConfigService.fixMembers(userInfoFromToken,partyId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build()
                ;
    }

    @Operation(summary = "Contact Rudder(Problem & Enquiry)")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "성공")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/customer-sound")
    public ResponseEntity sendCustomerSound(@RequestBody PartyDto.SendCustomerSoundRequest  sendCustomerSoundRequest) {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        partyConfigService.sendCustomerSound(userInfoFromToken,sendCustomerSoundRequest);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build()
                ;
    }


}
