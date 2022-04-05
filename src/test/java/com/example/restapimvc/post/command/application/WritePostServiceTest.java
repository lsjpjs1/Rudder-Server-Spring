package com.example.restapimvc.post.command.application;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.post.command.dto.WritePostDto;
import com.example.restapimvc.repository.UserInfoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class WritePostServiceTest {

    @Autowired
    private WritePostService writePostService;

    @Autowired
    private UserInfoRepository userInfoRepository;
    @Test
    void writePost() {
        UserInfo userInfo = userInfoRepository.findUserInfoByUserId("abcd").get();
        userInfo.getSchool();
        writePostService.writePost(userInfo,new WritePostDto.WritePostRequest(null,1l));
    }
}