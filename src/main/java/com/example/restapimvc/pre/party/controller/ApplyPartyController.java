package com.example.restapimvc.pre.party.controller;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.exception.ErrorResponse;
import com.example.restapimvc.pre.party.command.application.ApplyPartyService;
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

import java.util.ArrayList;
import java.util.Arrays;

@RestController
@RequiredArgsConstructor
@Api( tags = "파티 신청 관련")
public class ApplyPartyController {
    private final ApplyPartyService applyPartyService;

    @Operation(summary = "group apply 그룹 생성(삭제예정)")
    @PostMapping(value = "/parties/{partyId}/apply-group")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "성공")
    })
    public ResponseEntity<PartyDto.GetPartyApplicantsResponse> createPartyApplyGroup(@PathVariable Long partyId, @RequestBody PartyDto.CreatePartyApplyGroupRequest createPartyApplyGroupRequest) {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        createPartyApplyGroupRequest.setPartyId(partyId);
        applyPartyService.createPartyApplyGroup(userInfoFromToken,createPartyApplyGroupRequest);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();

    }

    @Operation(summary = "apply group에 참가(삭제예정)")
    @PostMapping(value = "/parties/apply-group/{applyGroupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "성공")
    })
    public ResponseEntity<PartyDto.GetPartyApplicantsResponse> joinPartyApplyGroup(@PathVariable Long applyGroupId) {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        applyPartyService.joinPartyApplyGroup(userInfoFromToken,applyGroupId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();

    }

    @Operation(summary = "party 참가 신청")
    @PostMapping(value = "/parties/{partyId}/apply")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "성공"),
            @ApiResponse(code = 406, message = "1.PARTY_CANCELED(취소된 파티)", response = ErrorResponse.class),
    })
    public ResponseEntity applyParty(@PathVariable Long partyId, @RequestBody PartyDto.ApplyPartyRequest applyPartyRequest) {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        applyPartyRequest.setPartyId(partyId);
        applyPartyService.applyParty(userInfoFromToken,applyPartyRequest);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();

    }

}
