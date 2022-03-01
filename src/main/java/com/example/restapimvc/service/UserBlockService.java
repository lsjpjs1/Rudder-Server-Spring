package com.example.restapimvc.service;

import com.example.restapimvc.domain.UserBlock;
import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.dto.UserBlockDTO;
import com.example.restapimvc.exception.CustomException;
import com.example.restapimvc.exception.ErrorCode;
import com.example.restapimvc.repository.UserBlockRepository;
import com.example.restapimvc.repository.UserInfoRepository;
import com.example.restapimvc.security.CustomSecurityContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserBlockService {

    private final UserBlockRepository userBlockRepository;

    private final UserInfoRepository userInfoRepository;

    public UserBlock createUserBlock(UserBlockDTO.CreateUserBlockRequest createUserBlockRequest){
        UserInfo userInfo = CustomSecurityContextHolder.getUserInfoFromToken();
        Optional<UserInfo> blockedUserInfo = userInfoRepository.findUserInfoByUserInfoId(createUserBlockRequest.getBlockUserInfoId());
        blockedUserInfo.orElseThrow(()-> new CustomException(ErrorCode.USER_NOT_FOUND));
        Optional<UserBlock> userBlockAlreadyExist = userBlockRepository.findByUserInfoAndBlockedUserInfo(userInfo, blockedUserInfo.get());
        userBlockAlreadyExist.ifPresent(
                s -> {
                    throw new CustomException(ErrorCode.DUPLICATE_RESOURCE);
                }
        );
        UserBlock userBlock = UserBlock.builder().blockedUserInfo(blockedUserInfo.get()).userInfo(userInfo).build();
        return userBlockRepository.save(userBlock);
    }
}
