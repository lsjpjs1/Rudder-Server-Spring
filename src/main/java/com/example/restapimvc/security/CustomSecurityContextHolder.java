package com.example.restapimvc.security;

import com.example.restapimvc.domain.UserInfo;
import org.springframework.security.core.context.SecurityContextHolder;

public class CustomSecurityContextHolder extends SecurityContextHolder {
    public static UserInfo getUserInfoFromToken() {
        return (UserInfo) getContext().getAuthentication().getPrincipal();
    }
}
