package com.example.restapimvc.enums;

import com.example.restapimvc.dto.NoticeDTO;
import com.example.restapimvc.exception.CustomException;
import com.example.restapimvc.exception.ErrorCode;
import com.example.restapimvc.util.EnumEntityConvertable;
import lombok.Getter;

import java.security.cert.TrustAnchor;
import java.util.Arrays;
import java.util.function.Function;

@Getter
public enum UserInfoOsType implements EnumEntityConvertable {
    ANDROID("android", "3.0"),
    IOS("ios", "4.2");

    private String str;
    private String newestVersion;

    UserInfoOsType(String str, String newestVersion) {
        this.str = str;
        this.newestVersion = newestVersion;
    }


    @Override
    public String getEntityValue() {
        return str;
    }

    @Override
    public Boolean isTargetEnum(String s) {
        return str.equals(s);
    }

    public static Boolean isNewestVersion(NoticeDTO.NoticeRequest noticeRequest) {
        Boolean isNewest = Boolean.FALSE;
        for (UserInfoOsType userInfoOsType : values()) {
            if (userInfoOsType.getStr().equals(noticeRequest.getOs()) && userInfoOsType.getNewestVersion().equals(noticeRequest.getVersion())) {
                isNewest = Boolean.TRUE;
                break;
            }
        }
        return isNewest;
    }

    public static UserInfoOsType fromString(String s) {
        UserInfoOsType targetUserInfoOsType = null;
        for(UserInfoOsType userInfoOsType: values()){
            if(userInfoOsType.getStr().equals(s)) {
                targetUserInfoOsType = userInfoOsType;
            }
        }
        return targetUserInfoOsType;
    }


}
