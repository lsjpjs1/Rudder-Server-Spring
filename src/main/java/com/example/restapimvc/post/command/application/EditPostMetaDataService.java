package com.example.restapimvc.post.command.application;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.exception.CustomException;
import com.example.restapimvc.exception.ErrorCode;
import com.example.restapimvc.post.command.domain.Post;
import com.example.restapimvc.post.command.domain.PostRepository;
import com.example.restapimvc.post.command.dto.PostMetaDataDto;
import com.example.restapimvc.util.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EditPostMetaDataService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    @Transactional
    public void viewPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
        post.view();
        postRepository.save(post);
    }

    @Transactional
    public PostMetaDataDto.LikePostResponse likePost(UserInfo userinfo, Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
        post.like(userinfo);
        postRepository.save(post);
        return postMapper.entityToLikePostResponse(post);
    }
}
