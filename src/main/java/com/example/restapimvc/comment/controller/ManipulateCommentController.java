package com.example.restapimvc.comment.controller;

import com.example.restapimvc.comment.command.application.ManipulateCommentService;
import com.example.restapimvc.comment.command.dto.CommentDto;
import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.exception.ErrorResponse;
import com.example.restapimvc.security.CustomSecurityContextHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Api( tags = "댓글 조작")
public class ManipulateCommentController {
    private final ManipulateCommentService manipulateCommentService;


    @Operation(summary = "댓글 작성", description = "Legacy: /comment/addComment")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "성공"),
            @ApiResponse(code = 404, message = "1.POST_NOT_FOUND(존재하지 않는 postId)", response = ErrorResponse.class)
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/comments")
    public ResponseEntity<CommentDto.CommonCommentResponse> writeComment(@RequestBody CommentDto.WriteCommentRequest writeCommentRequest) {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(manipulateCommentService.writeComment(userInfoFromToken,writeCommentRequest))
                ;
    }

    @Operation(summary = "댓글 수정", description = "Legacy: /comment/editComment")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "성공"),
            @ApiResponse(code = 404, message = "1.COMMENT_NOT_FOUND(존재하지 않는 commentId)", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "1.NO_PERMISSION(comment 수정 권한이 없음)", response = ErrorResponse.class)
    })
    @PatchMapping(value = "/comments/{commentId}")
    public ResponseEntity<CommentDto.CommonCommentResponse> editComment(@RequestBody CommentDto.EditCommentRequest editCommentRequest, @PathVariable Long commentId) {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        editCommentRequest.setCommentId(commentId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(manipulateCommentService.editComment(userInfoFromToken,editCommentRequest))
                ;
    }

    @Operation(summary = "댓글 수정", description = "Legacy: /comment/addLike")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "성공"),
            @ApiResponse(code = 404, message = "1.COMMENT_NOT_FOUND(존재하지 않는 commentId)", response = ErrorResponse.class)
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(value = "/comments/{commentId}/like")
    public ResponseEntity likeComment(@PathVariable Long commentId) {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        manipulateCommentService.likeComment(userInfoFromToken,commentId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build()
                ;
    }


    @Operation(summary = "댓글 삭제", description = "Legacy: /comment/deleteComment")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "성공"),
            @ApiResponse(code = 404, message = "1.COMMENT_NOT_FOUND(존재하지 않는 commentId)", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "1.NO_PERMISSION(comment 수정 권한이 없음)", response = ErrorResponse.class)

    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/comments/{commentId}")
    public ResponseEntity deletePost(@PathVariable Long commentId) {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        manipulateCommentService.deleteComment(userInfoFromToken, commentId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build()
                ;
    }

}
