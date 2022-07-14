package com.example.restapimvc.controller;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.dto.ReportDto;
import com.example.restapimvc.dto.UserBlockDTO;
import com.example.restapimvc.security.CustomSecurityContextHolder;
import com.example.restapimvc.service.ReportService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "통합 신고 api",description = "reportType: 신고할 대상의 종류 ex)유저,게시글,댓글 등, itemId: 신고할 대상의 id ex)userInfoId, postId, partyId")
    @PostMapping(value = "/reports", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity report(@RequestBody ReportDto.ReportRequest reportRequest) {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        reportService.report(userInfoFromToken,reportRequest);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
