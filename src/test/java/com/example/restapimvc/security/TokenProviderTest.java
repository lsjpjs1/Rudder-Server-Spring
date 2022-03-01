package com.example.restapimvc.security;

import com.example.restapimvc.dto.TokenDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest
class TokenProviderTest {
    private TokenProvider tokenProvider;

    @Autowired
    public TokenProviderTest(TokenProvider tokenProvider){
        this.tokenProvider = tokenProvider;
    }

    @Test
    public void testTokenProvider()  {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(Role.USER.getValue()));
        TokenDto tokenDto = tokenProvider.generateTokenDto(new UsernamePasswordAuthenticationToken("new token",null, grantedAuthorities));
        System.out.println(tokenDto.getAccessToken());
    }

    @Test
    public void testParseToken() {
    }

}