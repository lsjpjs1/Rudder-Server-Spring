package com.example.restapimvc.post.controller;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.dto.UserInfoDto;
import com.example.restapimvc.exception.ErrorResponse;
import com.example.restapimvc.post.command.application.EditPostService;
import com.example.restapimvc.post.command.dto.CommonPostDto;
import com.example.restapimvc.post.command.dto.EditPostDto;
import com.example.restapimvc.security.CustomSecurityContextHolder;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class EditPostController {

    private final EditPostService editPostService;

    /**
     * @param postId Long
     * @param editPostRequest
     *          String postBody 수정할 게시글 내용
     * @return 200
     *         Long postId;
     *         String userId;
     *         String postBody;
     *         Long categoryId;
     *         Long schoolId;
     *         Timestamp postTime;
     * @throws 404 POST_NOT_FOUND 존재하지 않는 postId
     * @throws 403 NO_PERMISSION 수정하려는 게시글이 내 게시글이 아님
     * @throws 406 BAD_REQUEST_CONTENT 파라미터에 null값 넘어옴
     */
    @Operation(summary = "게시글 수정", description = "Legacy: /board/editPost")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 404, message = "1.POST_NOT_FOUND(존재하지 않는 postId)", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "1.NO_PERMISSION(삭제할 권한 없음 ex) 내 게시글 아닌데 삭제하려고 할 때)", response = ErrorResponse.class),
            @ApiResponse(code = 406, message = "1.BAD_REQUEST_CONTENT(파라미터에 null값 넘어옴)", response = ErrorResponse.class),
    })
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
