package com.example.restapimvc.security;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.reactive.function.server.MockServerRequest;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.example.restapimvc.security.JwtFilter.AUTHORIZATION_HEADER;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class JwtFilterTest {

    private TokenProvider tokenProvider;

    private JwtFilter jwtFilter;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;


    @Autowired
    public JwtFilterTest(TokenProvider tokenProvider){
        this.tokenProvider = tokenProvider;
        this.jwtFilter = new JwtFilter(tokenProvider);
    }

    @Test
    public void testJwtFilter() throws ServletException, IOException {
        Mockito.when(request.getHeader(AUTHORIZATION_HEADER)).thenReturn("token");
        jwtFilter.doFilterInternal(request,response,filterChain);
    }
}