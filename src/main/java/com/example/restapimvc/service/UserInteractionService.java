package com.example.restapimvc.service;

import com.example.restapimvc.domain.UserBlock;
import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.dto.UserInteractionDTO;
import com.example.restapimvc.dto.UserTokenInfoDTO;
import com.example.restapimvc.repository.UserBlockRepository;
import com.example.restapimvc.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInteractionService {

    private final UserBlockRepository userBlockRepository;

    private final UserInfoRepository userInfoRepository;

    public UserInteractionDTO.BlockUserResponse blockUser(UserInteractionDTO.BlockUserRequest blockUserRequest){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserInfo userInfo = (UserInfo) principal;
        UserInfo blockedUserInfo = userInfoRepository.findUserInfoByUserInfoId(blockUserRequest.getBlockUserInfoId()).get();
        UserBlock userBlock = UserBlock.builder().blockedUserInfo(blockedUserInfo).userInfo(userInfo).build();
        userBlockRepository.save(userBlock);
        return new UserInteractionDTO.BlockUserResponse(true);
    }
}
