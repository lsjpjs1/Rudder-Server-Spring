package com.example.restapimvc.pre.party.controller;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.pre.party.command.application.GetPartyService;
import com.example.restapimvc.pre.party.command.dto.PartyDto;
import com.example.restapimvc.security.CustomSecurityContextHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api( tags = "파티 목록 조회 관련")
public class GetPartyController {
    private final GetPartyService getPartyService;

    @Operation(summary = "파티 목록 가져오기")
    @GetMapping(value = "/parties")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "성공")
    })
    public ResponseEntity<PartyDto.GetPartiesResponse> getParties(@ModelAttribute PartyDto.GetPartiesRequest getPartiesRequest) {

        log.debug(getPartiesRequest.getFilteringPeriod().name());
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(getPartyService.getParties(userInfoFromToken,getPartiesRequest));

    }
}
