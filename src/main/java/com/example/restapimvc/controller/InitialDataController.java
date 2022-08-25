package com.example.restapimvc.controller;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.dto.InitialDataDto;
import com.example.restapimvc.dto.PartyProfileDto;
import com.example.restapimvc.security.CustomSecurityContextHolder;
import com.example.restapimvc.service.InitialDataService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api( tags = "초기 데이터")
public class InitialDataController {

    private final InitialDataService initialDataService;

    @Operation(summary = "초기 데이터 조회")
    @GetMapping(value = "/initial-data")
    public ResponseEntity<InitialDataDto.InitialDataResponse> getInitialData() {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(initialDataService.getInitialData(userInfoFromToken));
    }

}
