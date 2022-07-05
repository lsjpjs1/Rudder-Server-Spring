package com.example.restapimvc.controller;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.dto.PartyProfileDto;
import com.example.restapimvc.dto.UserProfileDto;
import com.example.restapimvc.exception.ErrorResponse;
import com.example.restapimvc.post.command.dto.FileDto;
import com.example.restapimvc.post.command.dto.WritePostDto;
import com.example.restapimvc.security.CustomSecurityContextHolder;
import com.example.restapimvc.service.PartyProfileService;
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
@Api( tags = "파티 프로필 관련")
public class PartyProfileController {

    private final PartyProfileService partyProfileService;

    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "프로필 사진 S3용 업로드용 signedUrl 가져오기")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "성공"),
            @ApiResponse(code = 404, message = "1.BAD_REQUEST_CONTENT(imageMetaData가 빈 배열이거나 Null일때)", response = ErrorResponse.class),
            @ApiResponse(code = 406, message = "1.USER_INFO_NOT_FOUND(존재하지 않는 유저)", response = ErrorResponse.class)
    })
    @PostMapping(value = "/party-profile-image/image-upload-url/generate")
    public ResponseEntity<FileDto.UrlsWrapper> getS3SignedUrl(@RequestBody PartyProfileDto.PartyProfileImageUploadUrlRequest partyProfileImageUploadUrlRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(partyProfileService.getImageUploadUrl(partyProfileImageUploadUrlRequest))
                ;
    }

    @GetMapping(value = "/party-profiles/{userInfoId}")
    public ResponseEntity<PartyProfileDto.GetPartyProfileResponse> getPartyProfile(@PathVariable Long userInfoId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(partyProfileService.getPartyProfile(userInfoId));
    }
}