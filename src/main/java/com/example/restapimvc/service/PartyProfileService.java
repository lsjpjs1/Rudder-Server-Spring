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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PartyProfileService {

    private final UserInfoRepository userInfoRepository;
    private final UserPartyProfileRepository userPartyProfileRepository;
    private final UserPartyProfileImageRepository userPartyProfileImageRepository;
    private final FileUploadRepository fileUploadRepository;

    @Transactional
    public FileDto.UploadUrlsWrapper getImageUploadUrl(PartyProfileDto.PartyProfileImageUploadUrlRequest partyProfileImageUploadUrlRequest) {
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
                userPartyProfile.setPartyProfileImage(partyProfileImage);
                userPartyProfileRepository.save(userPartyProfile);
            }
        }

        return FileDto.UploadUrlsWrapper.builder().uploadUrls(fileUploadRepository.getFileUploadUrls(imageMetaData)).build();
    }
}
