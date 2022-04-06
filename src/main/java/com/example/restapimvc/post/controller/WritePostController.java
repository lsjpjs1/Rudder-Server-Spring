package com.example.restapimvc.post.controller;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.post.command.application.WritePostService;
import com.example.restapimvc.post.command.dto.CommonPostDto;
import com.example.restapimvc.post.command.dto.WritePostDto;
import com.example.restapimvc.security.CustomSecurityContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WritePostController {

    private final WritePostService writePostService;

    /**
     * Legacy: /board/addPost
     * @param writePostRequest
     *          String postBody(not null)
     *          Long categoryId(not null)
     * @return 201
     *         Long postId;
     *         String userId;
     *         String postBody;
     *         Long categoryId;
     *         Long schoolId;
     *         Timestamp postTime;
     * @throws 406 BAD_REQUEST_CONTENT, 파라미터로 null값 넘어올 때
     */
    @PostMapping(value = "/posts")
    public ResponseEntity<CommonPostDto.CommonPostResponse> forgotUserPassword(@RequestBody WritePostDto.WritePostRequest writePostRequest) {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(writePostService.writePost(userInfoFromToken,writePostRequest))
                ;
    }

}
