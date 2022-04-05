package com.example.restapimvc.post.query.application;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.repository.UserInfoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
class LookUpPostViewServiceTest {
    @Autowired
    private LookUpPostViewService lookUpPostViewService;
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Test
    void getPostViews() {
        UserInfo userInfo = userInfoRepository.findUserInfoByUserId("fff").get();
//        List<PostViewDTO.PostViewResponse> postViews = postViewService.getPostViews(userInfo, PostViewDTO.PostViewRequest.builder().build());
//        for(PostViewDTO.PostViewResponse postViewResponse: postViews) {
//            System.out.println(postViewResponse.toString());
//        }
    }
}