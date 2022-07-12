package com.example.restapimvc.controller;

import com.example.restapimvc.domain.RequestAddUniversity;
import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.dto.RequestAddUniversityDto;
import com.example.restapimvc.repository.RequestAddUniversityRepository;
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
@Api( tags = "없는 학교 요청 관련")
public class RequestAddUniversityController {
    private final RequestAddUniversityRepository requestAddUniversityRepository;

    @Operation(summary = "없는 학교 생성 요청")
    @PostMapping(value = "/schools/request")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "성공")
    })
    public ResponseEntity requestAddUniversity(@RequestBody RequestAddUniversityDto.RequestAddUniversityRequest requestAddUniversityRequest) {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        RequestAddUniversity requestAddUniversity = RequestAddUniversity.builder().universityName(requestAddUniversityRequest.getRequestUniversityName()).build();
        requestAddUniversityRepository.save(requestAddUniversity);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();

    }
}
