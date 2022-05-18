package com.example.restapimvc.post.controller;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.exception.ErrorResponse;
import com.example.restapimvc.post.command.application.DeletePostService;
import com.example.restapimvc.post.command.dto.WritePostDto;
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
@Api( tags = "게시글 삭제 관련")
public class DeletePostController {

    private final DeletePostService deletePostService;

    /**
     * Lecacy: /board/deletePost
     * @param postId Long
     * @return 204
     * @throws 404 POST_NOT_FOUND, 존재하지 않는 postId
     * @throws 403 NO_PERMISSION, 삭제할 권한 없음 ex) 내 게시글 아닌데 삭제하려고 할 때
     * @throws 406 ALREADY_PROCESSED, 이미 삭제된 게시글
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "게시글 삭제", description = "Legacy: /board/deletePost")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "성공"),
            @ApiResponse(code = 404, message = "1.POST_NOT_FOUND(존재하지 않는 postId)", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "1.NO_PERMISSION(삭제할 권한 없음 ex) 내 게시글 아닌데 삭제하려고 할 때)", response = ErrorResponse.class),
            @ApiResponse(code = 406, message = "1.ALREADY_PROCESSED(이미 삭제된 게시글)", response = ErrorResponse.class),
    })
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
