package com.example.restapimvc.security;

import org.junit.jupiter.api.Test;

public class Sha1PasswordEncoderTest {
    private Sha1PasswordEncoder sha1PasswordEncoder = new Sha1PasswordEncoder();
    @Test
    public void encoderTest(){
        System.out.println(sha1PasswordEncoder.encode("123123123a"));
    }
}
