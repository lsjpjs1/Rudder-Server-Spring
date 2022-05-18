package com.example.restapimvc.controller;


import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.domain.UserProfile;
import com.example.restapimvc.dto.UserInfoDto;
import com.example.restapimvc.dto.UserProfileDto;
import com.example.restapimvc.service.UserProfileService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user-profiles")
@RequiredArgsConstructor
@Api( tags = "유저 프로필 관련")
public class UserProfileController {

    private final UserProfileService userProfileService;

    /**
     * Legacy: /users/updateUserProfileImage
     * @param updateProfileImageRequest Long profileImageId : 해당 프로필 이미지 id로 업데이트
     * @return 201, String userId, String userNickname,
     * UserProfile userProfile{Long profileId, Long profileImageId, String profileBody}
     * @throws 404, PROFILE_IMAGE_ID_NOT_FOUND: 해당 프로필 이미지 id가 존재하지 않음
     */
    @PatchMapping(value = "/profileImage",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserInfoDto.UserInfoWithProfileResponse> updateUserProfileImage(@RequestBody UserProfileDto.UpdateProfileImageRequest updateProfileImageRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userProfileService.updateUserProfileImage(updateProfileImageRequest))
                ;
    }


    /**
     * Legacy: /signupin/profileImageUrl
     * @return 200, Long profileId, String profileBody, Long profileImageId
     * @throws 404, USER_PROFILE_NOT_FOUND 해당 유저의 profileId가 존재하지 않을 때
     */
    @GetMapping
    public ResponseEntity<UserProfileDto.UserProfileResponse> getUserProfile() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userProfileService.getUserProfile());
    }

}
