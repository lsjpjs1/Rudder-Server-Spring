package com.example.restapimvc.enums;

import com.example.restapimvc.exception.CustomException;
import com.example.restapimvc.exception.ErrorCode;
import com.example.restapimvc.util.EnumEntityConvertable;
import lombok.Getter;

import java.util.Arrays;
import java.util.function.Function;

@Getter
public enum UserInfoOsType implements EnumEntityConvertable {
    ANDROID("android"),
    IOS("ios")
    ;

    private String str;

    UserInfoOsType(String str) {
        this.str = str;
    }



    @Override
    public String getEntityValue() {
        return str;
    }

    @Override
    public Boolean isTargetEnum(String s) {
        return str.equals(s);
    }



}
