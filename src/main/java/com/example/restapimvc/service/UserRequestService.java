package com.example.restapimvc.service;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.domain.UserRequest;
import com.example.restapimvc.dto.UserRequestDTO;
import com.example.restapimvc.dto.UserTokenInfoDTO;
import com.example.restapimvc.repository.UserInfoRepository;
import com.example.restapimvc.repository.UserRequestRepository;
import com.example.restapimvc.security.CustomSecurityContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRequestService {
    private final UserRequestRepository userRequestRepository;

    public UserRequest createUserRequestResponse(UserRequestDTO.CreateUserRequestRequest createUserRequestRequest) {
        UserInfo userInfo = CustomSecurityContextHolder.getUserInfoFromToken();
        UserRequest userRequest = UserRequest.builder()
                .userInfo(userInfo)
                .body(createUserRequestRequest.getBody())
                .build();
        return userRequestRepository.save(userRequest);
    }
}
