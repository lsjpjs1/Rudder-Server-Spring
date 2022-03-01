package com.example.restapimvc.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder(builderMethodName = "tokenDtoBuilder")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class TokenDto {
    private String accessToken;

}
