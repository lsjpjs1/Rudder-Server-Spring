package com.example.restapimvc.controller;

import com.example.restapimvc.comment.command.application.LookUpCommentService;
import com.example.restapimvc.comment.command.dto.CommentDto;
import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.exception.CustomException;
import com.example.restapimvc.exception.ErrorCode;
import com.example.restapimvc.security.CustomSecurityContextHolder;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Api( tags = "이메일 검사")
public class ValidateEmailController {

    @Operation(summary = "이메일 유효성 검사")
    @GetMapping(value = "/email/{email}/validate")
    public ResponseEntity emailValidate(@PathVariable String email) {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        if(email.equals("mhpark0220@naver.com")){
            throw new CustomException(ErrorCode.WRONG_EMAIL_FORM);
        }
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
