package com.example.restapimvc.service;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.dto.TokenDto;
import com.example.restapimvc.dto.UserInfoDto;
import com.example.restapimvc.enums.UserInfoOsType;
import com.example.restapimvc.exception.CustomException;
import com.example.restapimvc.exception.ErrorCode;
import com.example.restapimvc.repository.UserInfoRepository;
import com.example.restapimvc.security.Role;
import com.example.restapimvc.security.Sha1PasswordEncoder;
import com.example.restapimvc.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserInfoRepository userInfoRepository;
    private final TokenProvider tokenProvider;
    private final Sha1PasswordEncoder sha1PasswordEncoder = Sha1PasswordEncoder.getInstance();

    public TokenDto login(UserInfoDto.LoginRequest loginRequest){
        if(loginRequest.getUserId()==null || loginRequest.getUserPassword()==null) {
            throw new CustomException(ErrorCode.BAD_REQUEST_CONTENT);
        }
        UserInfo userInfo = userInfoRepository.findUserInfoByUserId(loginRequest.getUserId())
                .orElseThrow(()->new CustomException(ErrorCode.USER_ID_NOT_FOUND));
        if(!sha1PasswordEncoder.matches(loginRequest.getUserPassword(),userInfo.getUserPassword())) {
            throw new CustomException(ErrorCode.PASSWORD_WRONG);
        }
        if (userInfo.getUserType().equals(1)) {
           throw new CustomException(ErrorCode.EMAIL_NOT_VERIFIED);
        }

        userInfo.setOs(UserInfoOsType.fromString(loginRequest.getOs()));
        userInfo.setNotificationToken(loginRequest.getNotificationToken());
        userInfoRepository.save(userInfo);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(Role.USER.getValue()));
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getUserId(),loginRequest.getUserPassword(),grantedAuthorities);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        return tokenProvider.generateTokenDto(authentication);

    }

}
