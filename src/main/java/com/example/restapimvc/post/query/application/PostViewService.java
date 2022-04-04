package com.example.restapimvc.post.query.application;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.exception.CustomException;
import com.example.restapimvc.exception.ErrorCode;
import com.example.restapimvc.post.query.dto.PostViewDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostViewService {
    private final PostViewRepository postViewRepository;
    private final PostViewQueryRepository postViewQueryRepository;

    @Transactional
    public PostViewDTO.PostViewResponseWrapper getPostViews(UserInfo userInfo, PostViewDTO.PostViewMultipleLookUpRequest postViewMultipleLookUpRequest) {
        postViewMultipleLookUpRequest.setAllUserInfo(userInfo);
        return new PostViewDTO.PostViewResponseWrapper(postViewQueryRepository.findPosts(postViewMultipleLookUpRequest));
    }

    @Transactional
    public PostViewDTO.PostViewResponse getPostViewByPostId(UserInfo userInfo, PostViewDTO.PostViewSingleLookUpRequest postViewSingleLookUpRequest) {
        postViewSingleLookUpRequest.setAllUserInfo(userInfo);
        postViewRepository.findById(postViewSingleLookUpRequest.getPostId())
                        .orElseThrow(()->new CustomException(ErrorCode.POST_NOT_FOUND));
        postViewRepository.findDistinctByPostIdAndIsDelete(postViewSingleLookUpRequest.getPostId(),Boolean.FALSE)
                        .orElseThrow(()->new CustomException(ErrorCode.POST_DELETED));
        postViewQueryRepository.findByPostIdAndBlock(postViewSingleLookUpRequest)
                .ifPresent((postView)->{throw new CustomException(ErrorCode.POST_WRITER_BLOCKED);});
        PostView postView = postViewQueryRepository.findByPostIdAndClubMember(postViewSingleLookUpRequest)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_CLUB_MEMBER));
        if (postView.getSchoolId()!=postViewSingleLookUpRequest.getSchoolId()) {
            throw new CustomException(ErrorCode.NOT_SCHOOL_MEMBER);
        }

        return postViewQueryRepository.findPostByPostId(postViewSingleLookUpRequest);
    }
}
