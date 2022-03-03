package com.example.restapimvc.security;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.exception.CustomException;
import com.example.restapimvc.exception.ErrorCode;
import com.example.restapimvc.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserInfoRepository userInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserInfo userInfo = userInfoRepository.findUserInfoByUserId(username).orElseThrow(()->new CustomException(ErrorCode.USER_ID_NOT_FOUND));
        return createUserDetails(userInfo);
    }


    private UserDetails createUserDetails(UserInfo userInfo){
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(Role.USER.getValue());
        return new User(userInfo.getUserId(),userInfo.getUserPassword(), Collections.singleton(grantedAuthority));
    }
}

