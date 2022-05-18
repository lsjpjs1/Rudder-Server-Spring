package com.example.restapimvc.comment.controller;

import com.example.restapimvc.category.command.application.LookUpCategoryService;
import com.example.restapimvc.category.command.dto.CategoryDto;
import com.example.restapimvc.comment.command.application.LookUpCommentService;
import com.example.restapimvc.comment.command.dto.CommentDto;
import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.security.CustomSecurityContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LookUpCommentController {
    private final LookUpCommentService lookUpCommentService;

//    @GetMapping(value = "/comments")
//    public ResponseEntity<CommentDto.GetCommentsResponse> getComments(@ModelAttribute CommentDto.GetCommentsRequest getCommentsRequest) {
//        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(lookUpCommentService.getCategories(userInfoFromToken, getCommentsRequest));
//    }
}
