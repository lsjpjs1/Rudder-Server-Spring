package com.example.restapimvc.comment.controller;

import com.example.restapimvc.category.command.application.LookUpCategoryService;
import com.example.restapimvc.category.command.dto.CategoryDto;
import com.example.restapimvc.comment.command.application.LookUpCommentService;
import com.example.restapimvc.comment.command.dto.CommentDto;
import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.security.CustomSecurityContextHolder;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api( tags = "댓글 조회")
public class LookUpCommentController {
    private final LookUpCommentService lookUpCommentService;

    @Operation(summary = "게시글에 달린 댓글 조회", description = "Legacy: /comment/showComment")
    @GetMapping(value = "/comments")
    public ResponseEntity<CommentDto.GetCommentsResponse> getComments(@ModelAttribute CommentDto.GetCommentsRequest getCommentsRequest) {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(lookUpCommentService.getComments(userInfoFromToken, getCommentsRequest));
    }
}
