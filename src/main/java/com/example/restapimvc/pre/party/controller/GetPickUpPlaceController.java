package com.example.restapimvc.pre.party.controller;

import com.example.restapimvc.pre.party.command.domain.Alcohol;
import com.example.restapimvc.pre.party.command.domain.PickUpPlace;
import com.example.restapimvc.pre.party.command.domain.PickUpPlaceRepository;
import com.example.restapimvc.pre.party.command.dto.PartyDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Api( tags = "픽업 장소 목록 조회 관련")
public class GetPickUpPlaceController {
    private final PickUpPlaceRepository pickUpPlaceRepository;

    @Operation(summary = "픽업 장소 목록 가져오기")
    @GetMapping(value = "/pick-up-places")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "성공")
    })
    public ResponseEntity<PartyDto.GetPickUpPlaceResponse> getPickUpPlaces() {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(PartyDto.GetPickUpPlaceResponse.builder().pickUpPlaces(pickUpPlaceRepository.findAll()).build());

    }
}
