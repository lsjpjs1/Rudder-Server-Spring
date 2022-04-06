package com.example.restapimvc.post.command.application;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.exception.CustomException;
import com.example.restapimvc.exception.ErrorCode;
import com.example.restapimvc.post.command.domain.Post;
import com.example.restapimvc.post.command.domain.PostRepository;
import com.example.restapimvc.post.command.dto.CommonPostDto;
import com.example.restapimvc.post.command.dto.EditPostDto;
import com.example.restapimvc.util.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EditPostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Transactional
    public CommonPostDto.CommonPostResponse editPost(UserInfo userInfo, EditPostDto.EditPostRequest editPostRequest) {
        Post post = postRepository.findById(editPostRequest.getPostId())
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
        if (!userInfo.getUserId().equals(post.getUserId())) {
            throw new CustomException(ErrorCode.NO_PERMISSION);
        }
        post.edit(editPostRequest);
        postRepository.save(post);
        return postMapper.entityToCommonPostResponse(post);
    }
}
