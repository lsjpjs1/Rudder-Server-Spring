package com.example.restapimvc.post.controller;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.post.command.application.DeletePostService;
import com.example.restapimvc.post.command.dto.WritePostDto;
import com.example.restapimvc.security.CustomSecurityContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class DeletePostController {

    private final DeletePostService deletePostService;

    /**
     * @param postId Long
     * @return 204
     * @throws 404 POST_NOT_FOUND, 존재하지 않는 postId
     * @throws 403 NO_PERMISSION, 삭제할 권한 없음 ex) 내 게시글 아닌데 삭제하려고 할 때
     * @throws 406 ALREADY_PROCESSED, 이미 삭제된 게시글
     */
    @DeleteMapping(value = "/posts/{postId}")
    public ResponseEntity deletePost(@PathVariable Long postId) {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        deletePostService.deletePost(userInfoFromToken,postId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build()
                ;
    }
}
