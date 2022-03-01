package com.example.restapimvc.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserBlockServiceTest {
    private UserBlockService userBlockService;

    @Autowired
    public UserBlockServiceTest(UserBlockService userBlockService){
        this.userBlockService = userBlockService;
    }

    @Test
    public void blockUserTest(){

    }
}