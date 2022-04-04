package com.example.restapimvc.post.query.application;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.post.query.dto.PostViewDTO;
import com.example.restapimvc.security.CustomSecurityContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostViewService {
    private final PostViewQueryRepository postViewQueryRepository;

    @Transactional
    public PostViewDTO.PostViewResponseWrapper getPostViews(UserInfo userInfo, PostViewDTO.PostViewRequest postViewRequest) {
        postViewRequest.setAllUserInfo(userInfo);
        return new PostViewDTO.PostViewResponseWrapper(postViewQueryRepository.findPosts(postViewRequest));
    }
}
