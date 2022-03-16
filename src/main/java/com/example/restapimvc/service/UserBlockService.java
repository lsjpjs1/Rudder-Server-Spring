package com.example.restapimvc.service;

import com.example.restapimvc.domain.UserBlock;
import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.dto.UserBlockDTO;
import com.example.restapimvc.exception.CustomException;
import com.example.restapimvc.exception.ErrorCode;
import com.example.restapimvc.repository.UserBlockRepository;
import com.example.restapimvc.repository.UserInfoRepository;
import com.example.restapimvc.security.CustomSecurityContextHolder;
import com.example.restapimvc.util.mapper.UserBlockMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserBlockService {

    private final UserBlockRepository userBlockRepository;

    private final UserInfoRepository userInfoRepository;

    private final UserBlockMapper userBlockMapper;

    public UserBlockDTO.CreateBlockUserResponse createUserBlock(UserBlockDTO.CreateUserBlockRequest createUserBlockRequest){
        UserInfo userInfo = CustomSecurityContextHolder.getUserInfoFromToken();
        Optional<UserInfo> blockedUserInfo = userInfoRepository.findUserInfoByUserInfoId(createUserBlockRequest.getBlockUserInfoId());
        blockedUserInfo.orElseThrow(()-> new CustomException(ErrorCode.USER_INFO_NOT_FOUND));
        userBlockRepository.findByUserInfoAndBlockedUserInfo(userInfo, blockedUserInfo.get()).ifPresent(
                s -> {throw new CustomException(ErrorCode.DUPLICATE_RESOURCE);}
        );
        UserBlock userBlock = UserBlock.builder().blockedUserInfo(blockedUserInfo.get()).userInfo(userInfo).build();
        return userBlockMapper.entityToCreateBlockUserResponse(userBlockRepository.save(userBlock));
    }
}
