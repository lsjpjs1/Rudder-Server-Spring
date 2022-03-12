package com.example.restapimvc.controller;


import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.domain.UserProfile;
import com.example.restapimvc.dto.UserInfoDto;
import com.example.restapimvc.dto.UserProfileDto;
import com.example.restapimvc.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user-profiles")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    /**
     * /users/updateUserProfileImage
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
}
