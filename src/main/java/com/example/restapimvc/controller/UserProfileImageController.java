package com.example.restapimvc.controller;

import com.example.restapimvc.dto.UserProfileDto;
import com.example.restapimvc.dto.UserProfileImageDTO;
import com.example.restapimvc.service.UserProfileImageService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user-profile-images")
@RequiredArgsConstructor
@Api( tags = "유저 프로필 이미지 조회")
public class UserProfileImageController {
    private final UserProfileImageService userProfileImageService;

    /**
     * Legacy: /signupin/profileImageList
     * @return 200, List<UserProfileImageWithURL> userProfileImages{
     *                  List[
     *                      Long userProfileImageId,
     *                      String highQualityUrl,
     *                      String previewUrl
     *                      ]
     *              }
     *              예시) {"userProfileImages":[{"userProfileImageId":1,"highQualityUrl":"http://d17a6yjghl1rix.cloudfront.net/profile_image_hd/1","previewUrl":"http://d17a6yjghl1rix.cloudfront.net/profile_image_preview/1"},{"userProfileImageId":2,"highQualityUrl":"http://d17a6yjghl1rix.cloudfront.net/profile_image_hd/2","previewUrl":"http://d17a6yjghl1rix.cloudfront.net/profile_image_preview/2"}]}
     */
    @GetMapping
    public ResponseEntity<UserProfileImageDTO.UserProfileImageResponse> getUserProfileImages() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userProfileImageService.getUserProfileImages());
    }
}
