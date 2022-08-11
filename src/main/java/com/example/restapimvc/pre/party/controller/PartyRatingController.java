package com.example.restapimvc.pre.party.controller;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.dto.PartyProfileDto;
import com.example.restapimvc.exception.ErrorResponse;
import com.example.restapimvc.post.command.dto.FileDto;
import com.example.restapimvc.pre.party.command.application.PartyRatingService;
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
@Api(tags = "파티 평점 관련")
public class PartyRatingController {

    private final PartyRatingService partyRatingService;

    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "파티 평점 매기기")
    @PostMapping(value = "/parties/{partyId}/rating")
    public ResponseEntity rateParty(@PathVariable Long partyId, @RequestBody PartyDto.RatePartyRequest ratePartyRequest) {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        ratePartyRequest.setPartyId(partyId);
        partyRatingService.rateParty(userInfoFromToken,ratePartyRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build()
                ;
    }

}
