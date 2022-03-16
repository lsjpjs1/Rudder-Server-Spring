package com.example.restapimvc.service;

import com.example.restapimvc.domain.UserProfileImage;
import com.example.restapimvc.dto.UserProfileImageDTO;
import com.example.restapimvc.repository.UserProfileImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserProfileImageService {

    private static String CLOUD_FRONT_URL_HIGH_QUALITY_PROFILE_IMAGE;

    private static String CLOUD_FRONT_URL_PREVIEW_PROFILE_IMAGE;

    private final UserProfileImageRepository userProfileImageRepository;

    @Autowired
    UserProfileImageService(@Value("${aws.cloud-front.url.high-quality-profile-image}") String high,
                        @Value("${aws.cloud-front.url.preview-profile-image}") String preview,
                            UserProfileImageRepository userProfileImageRepository){
        CLOUD_FRONT_URL_HIGH_QUALITY_PROFILE_IMAGE = high;
        CLOUD_FRONT_URL_PREVIEW_PROFILE_IMAGE = preview;
        this.userProfileImageRepository = userProfileImageRepository;
    }




    public UserProfileImageDTO.UserProfileImageResponse getUserProfileImages() {
        List<UserProfileImage> userProfileImages = userProfileImageRepository.findAll();
        List<UserProfileImageDTO.UserProfileImageWithURL> userProfileImageWithURLS =
                userProfileImages.stream()
                        .map(UserProfileImageService::userProfileImageWithURLFromEntity)
                        .collect(Collectors.toList());
        return UserProfileImageDTO.UserProfileImageResponse.builder()
                .userProfileImages(userProfileImageWithURLS)
                .build();
    }

    public static UserProfileImageDTO.UserProfileImageWithURL userProfileImageWithURLFromEntity(UserProfileImage userProfileImage) {
        return UserProfileImageDTO.UserProfileImageWithURL.builder()
                .userProfileImageId(userProfileImage.getUserProfileImageId())
                .highQualityUrl(CLOUD_FRONT_URL_HIGH_QUALITY_PROFILE_IMAGE +userProfileImage.getUserProfileImageId())
                .previewUrl(CLOUD_FRONT_URL_PREVIEW_PROFILE_IMAGE +userProfileImage.getUserProfileImageId())
                .build();
    }
}
