package com.example.restapimvc.post.controller;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.exception.ErrorResponse;
import com.example.restapimvc.post.command.application.EditPostMetaDataService;
import com.example.restapimvc.post.command.application.EditPostService;
import com.example.restapimvc.post.command.dto.PostMetaDataDto;
import com.example.restapimvc.security.CustomSecurityContextHolder;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EditPostMetaDataController {

    private final EditPostMetaDataService editPostMetaDataService;

    /**
     * Legacy: /board/addPostViewCount
     * @param postId Long
     * @return 204
     * @throws 404 POST_NOT_FOUND 존재하지 않는 postId
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "게시글 조회수 증가", description = "Legacy: /board/addPostViewCount")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "성공"),
            @ApiResponse(code = 404, message = "1.POST_NOT_FOUND(존재하지 않는 postId)", response = ErrorResponse.class)
    })
    @PatchMapping(value = "/posts/{postId}/view-count")
    public ResponseEntity viewPost(@PathVariable Long postId) {
        editPostMetaDataService.viewPost(postId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build()
                ;
    }

    /**
     * Legacy: /board/addLike
     * @param postId Long
     * @return 200
     *          Long postId
     *          Integer likeCount
     *          Boolean isImageExist 이미지 있으면 true
     * @throws 404 POST_NOT_FOUND 존재하지 않는 postId
     */
    @Operation(summary = "좋아요 클릭", description = "Legacy: /board/addLike")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 404, message = "1.POST_NOT_FOUND(존재하지 않는 postId)", response = ErrorResponse.class)
    })
    @PatchMapping(value = "/posts/{postId}/like-count")
    public ResponseEntity<PostMetaDataDto.LikePostResponse> likePost(@PathVariable Long postId) {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(editPostMetaDataService.likePost(userInfoFromToken, postId))
                ;
    }
}
