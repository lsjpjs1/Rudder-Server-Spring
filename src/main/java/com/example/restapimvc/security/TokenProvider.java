package com.example.restapimvc.security;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.dto.TokenDto;
import com.example.restapimvc.dto.UserTokenInfoDTO;
import com.example.restapimvc.repository.UserInfoRepository;
import com.example.restapimvc.util.ObjectMappingUtil;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TokenProvider {

    private static final String AUTHORITIES_KEY = "auth";

    private Key key;

    @Autowired
    private UserInfoRepository userInfoRepository;

    public TokenProvider(@Value("${jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public TokenDto generateTokenDto(Authentication authentication){
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        UserInfo userInfo = userInfoRepository.findUserInfoByUserId(authentication.getName()).get();
        userInfo.getSchool();
        //Payloads of JWT
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put(AUTHORITIES_KEY, authorities);

        HashMap<String, Object> userInfoHashMap = ObjectMappingUtil.objectToHashMap(userInfo);
        hashMap.putAll(userInfoHashMap);
        String accessToken = Jwts.builder()
                .setClaims(hashMap)
                .signWith(key,SignatureAlgorithm.HS512)
                .compact();

        return TokenDto.tokenDtoBuilder().accessToken(accessToken).build();
    }

    public Authentication getAuthentication(String accessToken) {
        Claims claims = parseClaims(accessToken);

        if (claims.get(AUTHORITIES_KEY) == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }



        // 클레임에서 권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());


        //Parse payloads
        UserInfo userInfo = ObjectMappingUtil.hashMapToObject(claims, UserInfo.class);
        return new UsernamePasswordAuthenticationToken(userInfo, "", authorities);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }

    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}
