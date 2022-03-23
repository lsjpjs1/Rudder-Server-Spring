package com.example.restapimvc.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return Sha1PasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                // 로그인, 회원가입 API 는 토큰이 없는 상태에서 요청이 들어오기 때문에 permitAll 설정
                .and()
                .authorizeRequests()
                .antMatchers("/schools").permitAll()
                .antMatchers("/notice").permitAll()
                .antMatchers("/user-profile-images").permitAll()
                .antMatchers("/user-infos").permitAll()
                .antMatchers("/user-infos/**/**/duplication-check").permitAll()
                .antMatchers("/user-infos/**/**/find-user-id").permitAll()
                .antMatchers("/user-infos/**/**/find-user-password").permitAll()
                .antMatchers("/user-infos/user-email/**/verify").permitAll()
                .antMatchers("/user-infos/user-email/**/validate").permitAll()
                .antMatchers("/auth").permitAll()
                .anyRequest().authenticated()   // 나머지 API 는 전부 인증 필요
                .and()
                .apply(new JwtSecurityConfig(tokenProvider));
    }
}
