package com.example.restapimvc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserTokenInfoDTO {
    private long userInfoId;
    private String userId;
    private long schoolId;

    @Override
    public String toString() {
        return "UserTokenInfoDTO{" +
                "userInfoId=" + userInfoId +
                ", userId='" + userId + '\'' +
                ", schoolId=" + schoolId +
                '}';
    }
}
