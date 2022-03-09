package com.example.restapimvc.service;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.dto.UserInfoDto;
import com.example.restapimvc.exception.CustomException;
import com.example.restapimvc.exception.ErrorCode;
import com.example.restapimvc.repository.UserInfoRepository;
import com.example.restapimvc.repository.UserProfileImageRepository;
import com.example.restapimvc.security.CustomSecurityContextHolder;
import com.example.restapimvc.util.mapper.UserInfoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserInfoService {

    private final UserInfoRepository userInfoRepository;

    private final UserInfoMapper userInfoMapper;


    public UserInfoDto.UserInfoResponse updateUserNickname(UserInfoDto.UpdateNicknameRequest updateNicknameRequest) {
        userInfoRepository.findUserInfoByUserNickname(updateNicknameRequest.getNickname()).ifPresent(
                p -> {throw new CustomException(ErrorCode.DUPLICATE_RESOURCE);}
        );
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        Optional<UserInfo> targetUserInfo = userInfoRepository.findUserInfoByUserInfoId(userInfoFromToken.getUserInfoId());
        targetUserInfo.orElseThrow(()-> new CustomException(ErrorCode.USER_INFO_NOT_FOUND));
        targetUserInfo.get().setUserNickname(updateNicknameRequest.getNickname());
        userInfoRepository.save(targetUserInfo.get());
        return userInfoMapper.entityToUserInfoResponse(targetUserInfo.get());
    }



}
