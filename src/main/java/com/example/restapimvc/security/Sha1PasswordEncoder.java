package com.example.restapimvc.security;

import lombok.SneakyThrows;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

// 싱글턴
public class Sha1PasswordEncoder implements PasswordEncoder {

    private static final Sha1PasswordEncoder instance = new Sha1PasswordEncoder();
    private final String HMAC_SHA1 = "HmacSHA1";

    @Value("${password.secret}")
    private String SECRET_KEY;

    private Sha1PasswordEncoder() {}

    public static Sha1PasswordEncoder getInstance() {
        return instance;
    }
    private final Log logger = LogFactory.getLog(getClass());
    @SneakyThrows
    @Override
    public String encode(CharSequence rawPassword) {
        if (rawPassword == null) {
            throw new IllegalArgumentException("rawPassword cannot be null");
        }
        SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes("utf-8"),HMAC_SHA1);
        Mac hasher = Mac.getInstance(HMAC_SHA1);
        hasher.init(secretKey);
        byte[] hash = hasher.doFinal(rawPassword.toString().getBytes());
        return Base64.encodeBase64String(hash);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (rawPassword == null){
            throw new IllegalArgumentException("rawPassword cannot be null");
        }
        if (encodedPassword == null || encodedPassword.length()== 0){
            this.logger.warn("Empty encoded password");
            return false;
        }
        if (encode(rawPassword).equals(encodedPassword)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean upgradeEncoding(String encodedPassword) {
        return false;
    }
}
