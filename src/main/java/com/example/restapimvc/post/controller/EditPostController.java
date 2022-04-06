package com.example.restapimvc.post.controller;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.dto.UserInfoDto;
import com.example.restapimvc.post.command.application.EditPostService;
import com.example.restapimvc.post.command.dto.CommonPostDto;
import com.example.restapimvc.post.command.dto.EditPostDto;
import com.example.restapimvc.security.CustomSecurityContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class EditPostController {

    private final EditPostService editPostService;

    @PatchMapping(value = "/posts/{postId}")
    public ResponseEntity<CommonPostDto.CommonPostResponse> editPost(
            @PathVariable Long postId,
            @RequestBody EditPostDto.EditPostRequest editPostRequest
            ) {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        editPostRequest.setPostId(postId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(editPostService.editPost(userInfoFromToken,editPostRequest))
                ;
    }
}
