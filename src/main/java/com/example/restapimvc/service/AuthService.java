package com.example.restapimvc.service;

import com.example.restapimvc.dto.TokenDto;
import com.example.restapimvc.dto.UserInfoDto;
import com.example.restapimvc.security.Role;
import com.example.restapimvc.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;

    public TokenDto login(UserInfoDto.LoginRequest loginRequest){
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(Role.USER.getValue()));
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getUserId(),loginRequest.getUserPassword(),grantedAuthorities);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        return tokenProvider.generateTokenDto(authentication);

    }

}
