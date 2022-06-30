package com.example.restapimvc.controller;

import com.example.restapimvc.comment.command.application.LookUpCommentService;
import com.example.restapimvc.comment.command.dto.CommentDto;
import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.dto.SchoolDTO;
import com.example.restapimvc.exception.CustomException;
import com.example.restapimvc.exception.ErrorCode;
import com.example.restapimvc.exception.ErrorResponse;
import com.example.restapimvc.security.CustomSecurityContextHolder;
import com.example.restapimvc.service.ValidateEmailService;
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
@Api( tags = "이메일 검사")
public class ValidateEmailController {

    private final ValidateEmailService validateEmailService;

    @Operation(summary = "이메일 유효성 검사")
    @GetMapping(value = "/email/{email}/validate")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 406, message = "1.WRONG_EMAIL_FORM(잘못된 이메일 형식)", response = ErrorResponse.class),
            @ApiResponse(code = 409, message = "1.EMAIL_ALREADY_EXIST(이미 존재하는 이메일)", response = ErrorResponse.class)
    })
    public ResponseEntity<SchoolDTO.SchoolForResponse> emailValidate(@PathVariable String email) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(validateEmailService.emailValidate(email));
    }
}
