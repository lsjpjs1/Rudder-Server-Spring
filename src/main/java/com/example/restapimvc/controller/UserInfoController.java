package com.example.restapimvc.controller;

import com.example.restapimvc.dto.UserInfoDto;
import com.example.restapimvc.exception.ErrorResponse;
import com.example.restapimvc.service.UserInfoService;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user-infos")
@RequiredArgsConstructor
public class UserInfoController {

    private final UserInfoService userInfoService;

    /**
     * Legacy: /users/updateNickname
     *
     * @param updateNicknameRequest String nickname
     * @return 201, String userId, String userNickname
     * @throws 409, DUPLICATE_RESOURCE 이미 존재하는 닉네임
     */
    @PatchMapping(value = "/nickname", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserInfoDto.UserInfoResponse> updateUserNickname(@RequestBody UserInfoDto.UpdateNicknameRequest updateNicknameRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userInfoService.updateUserNickname(updateNicknameRequest))
                ;
    }

    /**
     * Legacy: /signupin/logout
     *
     * @return 204, 응답 body 없음
     */
    @PostMapping(value = "/logout", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity logout() {
        userInfoService.logout();
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @Operation(summary = "회원가입", description = "Legacy: /signupin/signupinsert")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "성공"),
            @ApiResponse(code = 406, message = "1.WRONG_EMAIL_FORM(지원하지 않는 이메일 형식)", response = ErrorResponse.class),
            @ApiResponse(code = 409, message = "1.EMAIL_ALREADY_EXIST(이미 존재하는 이메일)", response = ErrorResponse.class)
    })
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<UserInfoDto.UserInfoEntireResponse> signUp(
            @RequestBody UserInfoDto.SignUpRequest signUpRequest
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userInfoService.signUp(signUpRequest));
    }

    /**
     * Legacy: /signupin/checkduplication
     *
     * @param userId String
     * @return 204
     * @throws 409 DUPLICATE_RESOURCE 아이디 중복
     */
    @PostMapping(value = "/user-id/{userId}/duplication-check")
    public ResponseEntity userIdDuplicationCheck(@PathVariable("userId") String userId) {
        userInfoService.userIdDuplicationCheck(userId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    /**
     * Legacy: /signupin/checkDuplicationNickname
     *
     * @param nickname String
     * @return 204
     * @throws 409 DUPLICATE_RESOURCE 닉네임 중복
     */
    @PostMapping(value = "/user-nickname/{nickname}/duplication-check")
    public ResponseEntity userNicknameDuplicationCheck(@PathVariable("nickname") String nickname) {
        userInfoService.userNicknameDuplicationCheck(nickname);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    /**
     * Legacy: /schoolverify/verifyEmail
     *
     * @param userEmail            String
     * @param validateEmailRequest Long schoolId
     * @return 204
     * @throws 409 DUPLICATE_RESOURCE 이메일 중복
     * @throws 404 SCHOOL_ID_NOT_FOUND 해당 schoolId 없음
     * @throws 406 WRONG_EMAIL_FORM 이메일 형식 잘못됨
     * @throws 500 SEND_EMAIL_FAIL, 이메일 전송 실패
     */
    @PostMapping(value = "/user-email/{userEmail}/validate")
    public ResponseEntity validateEmail(@PathVariable("userEmail") String userEmail,
                                        @RequestBody UserInfoDto.ValidateEmailRequest validateEmailRequest) {
        userInfoService.validateEmail(userEmail, validateEmailRequest);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @GetMapping(value = "/{userInfoId}/verification/{verificationCode}")
    public ResponseEntity verifyUser(@PathVariable("userInfoId") Long userInfoId,
                                     @PathVariable("verificationCode") String verificationCode) {
        userInfoService.verifyUser(userInfoId, verificationCode);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Verification success!");
    }

    /**
     * Legacy: /signupin/sendIdToEmail
     *
     * @param userEmail
     * @return 204
     * @throws 404 USER_EMAIL_NOT_FOUND, 존재하지 않는 이메일
     * @throws 500 SEND_EMAIL_FAIL, 이메일 전송 실패
     */
    @PostMapping(value = "/user-email/{userEmail}/find-user-id")
    public ResponseEntity forgotUserId(@PathVariable("userEmail") String userEmail) {
        userInfoService.forgotUserId(userEmail);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build()
                ;
    }

    /**
     * Legacy: /signupin/sendPwVerificationCode
     *
     * @param userEmail String
     * @return 204
     * @throws 404 USER_EMAIL_NOT_FOUND, 존재하지 않는 이메일
     * @throws 500 SEND_EMAIL_FAIL, 이메일 전송 실패
     */
    @PostMapping(value = "/user-email/{userEmail}/verify")
    public ResponseEntity sendVerificationCode(@PathVariable("userEmail") String userEmail) {
        userInfoService.sendVerificationCode(userEmail);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build()
                ;
    }

    /**
     * Legacy: /signupin/checkCode
     *
     * @param userEmail                    String
     * @param checkVerificationCodeRequest String verificationCode
     * @return 204
     * @throws 404 USER_EMAIL_NOT_FOUND, 존재하지 않는 이메일
     * @throws 404 VERIFICATION_CODE_NOT_FOUND, 해당 이메일로 전송된 verificationCode가 없을 때
     * @throws 404 VERIFICATION_CODE_WRONG, verificationCode 틀림
     * @throws 500 SEND_EMAIL_FAIL, 이메일 전송 실패
     */
    @PostMapping(value = "/user-email/{userEmail}/find-user-password")
    public ResponseEntity forgotUserPassword(@PathVariable("userEmail") String userEmail,
                                             @RequestBody UserInfoDto.CheckVerificationCodeRequest checkVerificationCodeRequest) {
        userInfoService.forgotUserPassword(userEmail, checkVerificationCodeRequest);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build()
                ;
    }

    /**
     * @param userEmail                    String
     * @param checkVerificationCodeRequest String verificationCode
     * @return 204
     * @throws 404 USER_EMAIL_NOT_FOUND, 존재하지 않는 이메일
     * @throws 404 VERIFICATION_CODE_NOT_FOUND, 해당 이메일로 전송된 verificationCode가 없을 때
     * @throws 404 VERIFICATION_CODE_WRONG, verificationCode 틀림
     */
    @PostMapping(value = "/user-email/{userEmail}/check-verification-code")
    public ResponseEntity checkVerificationCode(@PathVariable("userEmail") String userEmail,
                                                @RequestBody UserInfoDto.CheckVerificationCodeRequest checkVerificationCodeRequest) {
        userInfoService.checkVerificationCode(userEmail, checkVerificationCodeRequest);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build()
                ;
    }


}
