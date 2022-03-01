package com.example.restapimvc.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserInteractionServiceTest {
    private UserInteractionService userInteractionService;

    @Autowired
    public UserInteractionServiceTest(UserInteractionService userInteractionService){
        this.userInteractionService = userInteractionService;
    }

    @Test
    public void blockUserTest(){

    }
}