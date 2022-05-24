package com.example.restapimvc.controller;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.dto.ReportDto;
import com.example.restapimvc.dto.UserBlockDTO;
import com.example.restapimvc.security.CustomSecurityContextHolder;
import com.example.restapimvc.service.ReportService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Api( tags = "신고 관련")
public class ReportController {

    private final ReportService reportService;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping(value = "/reports", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity report(@RequestBody ReportDto.ReportRequest reportRequest) {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        reportService.report(userInfoFromToken,reportRequest);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
