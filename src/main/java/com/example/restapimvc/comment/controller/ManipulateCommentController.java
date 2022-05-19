package com.example.restapimvc.comment.controller;

import com.example.restapimvc.comment.command.application.ManipulateCommentService;
import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.post.command.dto.CommonPostDto;
import com.example.restapimvc.post.command.dto.WritePostDto;
import com.example.restapimvc.security.CustomSecurityContextHolder;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api( tags = "댓글 조작")
public class ManipulateCommentController {
    private final ManipulateCommentService manipulateCommentService;

//    @ResponseStatus(HttpStatus.CREATED)
//    @PostMapping(value = "/comments")
//    public ResponseEntity writeComment(@RequestBody WritePostDto.WritePostRequest writePostRequest) {
//        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
//        return ResponseEntity
//                .status(HttpStatus.CREATED)
//                .body(manipulateCommentService.writePost(userInfoFromToken,writePostRequest))
//                ;
//    }
}
