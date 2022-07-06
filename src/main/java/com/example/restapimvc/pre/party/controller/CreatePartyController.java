package com.example.restapimvc.pre.party.controller;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.pre.party.command.application.CreatePartyService;
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
@Api(tags = "파티 생성 관련")
public class CreatePartyController {

    private final CreatePartyService createPartyService;

    @Operation(summary = "파티 생성")
    @PostMapping(value = "/parties")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "성공")
    })
    public ResponseEntity createParty(@RequestBody PartyDto.CreatePartyRequest createPartyRequest) {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        createPartyService.createParty(userInfoFromToken,createPartyRequest);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();

    }
}
