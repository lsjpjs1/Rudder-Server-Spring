package com.example.restapimvc.pre.party.controller;

import com.example.restapimvc.pre.party.command.domain.Alcohol;
import com.example.restapimvc.pre.party.command.domain.AlcoholRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Api( tags = "술 목록 조회 관련")
public class GetAlcoholController {
    private final AlcoholRepository alcoholRepository;

    @Value("${cloud-front.url.post-image}")
    private String CLOUD_FRONT_POST_IMAGE_URL;

    @Operation(summary = "술 목록 가져오기")
    @GetMapping(value = "/alcohol")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "성공")
    })
    public ResponseEntity<List<Alcohol>> getAlcohol() {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(alcoholRepository.findAll().stream()
                        .map(alcohol -> {
                            alcohol.setAlcoholImageName(CLOUD_FRONT_POST_IMAGE_URL+alcohol.getAlcoholImageName());
                            return alcohol;
                        })
                        .collect(Collectors.toList()));

    }
}
