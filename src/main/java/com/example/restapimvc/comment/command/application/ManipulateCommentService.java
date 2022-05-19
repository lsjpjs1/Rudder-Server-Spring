package com.example.restapimvc.comment.command.application;

import com.example.restapimvc.comment.command.domain.CommentRepository;
import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.exception.CustomException;
import com.example.restapimvc.exception.ErrorCode;
import com.example.restapimvc.post.command.domain.Post;
import com.example.restapimvc.post.command.dto.CommonPostDto;
import com.example.restapimvc.post.command.dto.WritePostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ManipulateCommentService {
    private final CommentRepository commentRepository;

//    @Transactional
//    public void writeComment(UserInfo userInfo, ) {
//
//    }

}
