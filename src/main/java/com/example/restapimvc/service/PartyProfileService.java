package com.example.restapimvc.service;

import com.example.restapimvc.common.FileMetaData;
import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.domain.UserPartyProfile;
import com.example.restapimvc.domain.UserPartyProfileImage;
import com.example.restapimvc.dto.PartyProfileDto;
import com.example.restapimvc.exception.CustomException;
import com.example.restapimvc.exception.ErrorCode;
import com.example.restapimvc.post.command.domain.FileUploadRepository;
import com.example.restapimvc.post.command.domain.Post;
import com.example.restapimvc.post.command.dto.FileDto;
import com.example.restapimvc.post.command.dto.WritePostDto;
import com.example.restapimvc.repository.UserInfoRepository;
import com.example.restapimvc.repository.UserPartyProfileImageRepository;
import com.example.restapimvc.repository.UserPartyProfileRepository;
import com.example.restapimvc.util.RandomNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PartyProfileService {

    @Value("${cloud-front.url.post-image}")
    private String CLOUD_FRONT_POST_IMAGE_URL;

    private final UserInfoRepository userInfoRepository;
    private final UserPartyProfileRepository userPartyProfileRepository;
    private final UserPartyProfileImageRepository userPartyProfileImageRepository;
    private final FileUploadRepository fileUploadRepository;

    @Transactional
    public FileDto.UrlsWrapper getImageUploadUrl(PartyProfileDto.PartyProfileImageUploadUrlRequest partyProfileImageUploadUrlRequest) {
        if (partyProfileImageUploadUrlRequest.getImageMetaData() == null) {
            throw new CustomException(ErrorCode.BAD_REQUEST_CONTENT);
        }
        if (partyProfileImageUploadUrlRequest.getImageMetaData().isEmpty()) {
            throw new CustomException(ErrorCode.BAD_REQUEST_CONTENT);
        }
        UserInfo userInfo = userInfoRepository.findById(partyProfileImageUploadUrlRequest.getUserInfoId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_INFO_NOT_FOUND));
        UserPartyProfile userPartyProfile = userInfo.getUserPartyProfile();
        List<String> fileNames = new ArrayList<>();
        for (int i=0; i<partyProfileImageUploadUrlRequest.getImageMetaData().size();i++) {
            fileNames.add(new Date().getTime() + RandomNumber.generateRandomCode(6));
        }
        List<FileMetaData> imageMetaData = partyProfileImageUploadUrlRequest.getImageMetaData();
        for (int i=0; i<imageMetaData.size();i++) {
            imageMetaData.get(i).setFileName(fileNames.get(i));
        }

        for (int i=0; i<imageMetaData.size();i++){

            UserPartyProfileImage partyProfileImage = UserPartyProfileImage.builder()
                    .partyProfileId(userPartyProfile.getPartyProfileId())
                    .partyProfileImageName(imageMetaData.get(i).getFileName())
                    .build();
            userPartyProfileImageRepository.save(partyProfileImage);
            if(i==0){
                userPartyProfile.setUserPartyProfileImageId(partyProfileImage.getPartyProfileImageId());
                userPartyProfileRepository.save(userPartyProfile);
            }
        }

        return FileDto.UrlsWrapper.builder().urls(fileUploadRepository.getFileUploadUrls(imageMetaData).stream().map(uploadUrl -> uploadUrl.getUrl()).collect(Collectors.toList())).build();
    }

    @Transactional
    public PartyProfileDto.GetPartyProfileResponse getPartyProfile(Long targetUserInfoId) {
        UserInfo userInfo = userInfoRepository.findById(targetUserInfoId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_INFO_NOT_FOUND));
        UserPartyProfile userPartyProfile = userInfo.getUserPartyProfile();
        List<UserPartyProfileImage> userPartyProfileImages = userPartyProfileImageRepository.findByPartyProfileId(userPartyProfile.getPartyProfileId());
        List<String> partyProfileImages = userPartyProfileImages.stream()
                .map(userPartyProfileImage -> CLOUD_FRONT_POST_IMAGE_URL + userPartyProfileImage.getPartyProfileImageName())
                .collect(Collectors.toList());
        return PartyProfileDto.GetPartyProfileResponse.builder()
                .partyProfileId(userPartyProfile.getPartyProfileId())
                .partyProfileBody(userPartyProfile.getPartyProfileBody())
                .partyProfileImages(partyProfileImages)
                .build();

    }
}
