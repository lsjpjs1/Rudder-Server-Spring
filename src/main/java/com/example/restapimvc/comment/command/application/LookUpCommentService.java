package com.example.restapimvc.comment.command.application;

import com.example.restapimvc.category.command.dto.CategoryDto;
import com.example.restapimvc.comment.command.domain.CommentQueryRepository;
import com.example.restapimvc.comment.command.dto.CommentDto;
import com.example.restapimvc.domain.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LookUpCommentService {

    private final CommentQueryRepository commentQueryRepository;
//    @Transactional
//    public CommentDto.GetCommentsResponse getCategories(UserInfo userInfo, CommentDto.GetCommentsRequest getCommentsRequest) {
//        getCommentsRequest.setAllUserInfo(userInfo);
//        commentQueryRepository
//
//    }
}
