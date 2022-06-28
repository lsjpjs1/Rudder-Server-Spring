package com.example.restapimvc.controller;

import com.example.restapimvc.comment.command.application.LookUpCommentService;
import com.example.restapimvc.comment.command.dto.CommentDto;
import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.exception.CustomException;
import com.example.restapimvc.exception.ErrorCode;
import com.example.restapimvc.exception.ErrorResponse;
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
@Api( tags = "이메일 검사")
public class ValidateEmailController {

    @Operation(summary = "이메일 유효성 검사")
    @GetMapping(value = "/email/{email}/validate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "성공"),
            @ApiResponse(code = 406, message = "1.WRONG_EMAIL_FORM(잘못된 이메일 형식) mhpark0220@naver.com", response = ErrorResponse.class),
            @ApiResponse(code = 409, message = "1.EMAIL_ALREADY_EXIST(이미 존재하는 이메일) mhpark0220@gmail.com", response = ErrorResponse.class)
    })
    public ResponseEntity emailValidate(@PathVariable String email) {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        if(email.equals("mhpark0220@naver.com")){
            throw new CustomException(ErrorCode.WRONG_EMAIL_FORM);
        }
        if(email.equals("mhpark0220@gmail.com")){
            throw new CustomException(ErrorCode.EMAIL_ALREADY_EXIST);
        }
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
