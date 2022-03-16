package com.example.restapimvc.controller;

import com.example.restapimvc.dto.UserProfileDto;
import com.example.restapimvc.dto.UserProfileImageDTO;
import com.example.restapimvc.service.UserProfileImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user-profile-images")
@RequiredArgsConstructor
public class UserProfileImageController {
    private final UserProfileImageService userProfileImageService;

    /**
     * /signupin/profileImageList
     * @return 200, List<UserProfileImageWithURL> userProfileImages{
     *                  List[
     *                      Long userProfileImageId,
     *                      String highQualityUrl,
     *                      String previewUrl
     *                      ]
     *              }
     */
    @GetMapping
    public ResponseEntity<UserProfileImageDTO.UserProfileImageResponse> getUserProfileImages() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userProfileImageService.getUserProfileImages());
    }
}
