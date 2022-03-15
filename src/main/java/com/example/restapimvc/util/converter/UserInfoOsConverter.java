package com.example.restapimvc.util.converter;

import com.example.restapimvc.enums.UserInfoOsType;
import com.example.restapimvc.util.EnumEntityConverter;

import javax.persistence.Converter;

@Converter
public class UserInfoOsConverter extends EnumEntityConverter {
    public UserInfoOsConverter() {
        super(UserInfoOsType.class);
    }
}
