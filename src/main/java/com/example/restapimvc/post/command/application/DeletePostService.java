package com.example.restapimvc.post.command.application;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.exception.CustomException;
import com.example.restapimvc.exception.ErrorCode;
import com.example.restapimvc.post.command.domain.Post;
import com.example.restapimvc.post.command.domain.PostRepository;
import com.example.restapimvc.post.command.dto.WritePostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeletePostService {
    private final PostRepository postRepository;

    @Transactional
    public void deletePost(UserInfo userInfo, Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
        if (!userInfo.getUserId().equals(post.getUserId())) {
            throw new CustomException(ErrorCode.NO_PERMISSION);
        }
        if (post.getPostMetaData().getIsDelete()) {
            throw new CustomException(ErrorCode.ALREADY_PROCESSED);
        }
        post.delete();
        postRepository.save(post);
    }
}
