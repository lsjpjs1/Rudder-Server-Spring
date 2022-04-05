package com.example.restapimvc.post.command.application;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.exception.CustomException;
import com.example.restapimvc.exception.ErrorCode;
import com.example.restapimvc.post.command.domain.Post;
import com.example.restapimvc.post.command.domain.PostMetaData;
import com.example.restapimvc.post.command.domain.PostRepository;
import com.example.restapimvc.post.command.dto.WritePostDto;
import com.example.restapimvc.util.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
@RequiredArgsConstructor
public class WritePostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @PersistenceContext
    private final EntityManager entityManager;

    @Transactional
    public WritePostDto.WritePostResponse writePost(UserInfo userInfo, WritePostDto.WritePostRequest writePostRequest) {
        if (writePostRequest.getPostBody()==null || writePostRequest.getCategoryId()==null) {
            throw new CustomException(ErrorCode.BAD_REQUEST_CONTENT);
        }
        Post post = Post.builder()
                .userId(userInfo.getUserId())
                .postBody(writePostRequest.getPostBody())
                .categoryId(writePostRequest.getCategoryId())
                .schoolId(userInfo.getSchool().getSchoolId())
                .build();
        postRepository.save(post);
        entityManager.refresh(post);
        System.out.println(post);
        return postMapper.entityToWritePostResponse(post);
    }
}
