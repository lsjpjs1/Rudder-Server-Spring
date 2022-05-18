package com.example.restapimvc.post.controller;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.exception.ErrorResponse;
import com.example.restapimvc.post.query.application.LookUpPostViewService;
import com.example.restapimvc.post.query.dto.PostViewDTO;
import com.example.restapimvc.security.CustomSecurityContextHolder;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import jdk.jpackage.internal.Log;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LookUpPostController {

    private final LookUpPostViewService lookUpPostViewService;


    /**
     * Legacy: /board/renderPost
     * @param postViewMultipleLookUpRequest 파라미터 조합해서 사용가능
     *          Long endPostId(nullable) : endPostId 이후 게시글부터 검색
     *          Long categoryId(nullable) : categoryId로 검색
     *          String searchBody(nullable) : 검색어
     *          Long writerUserInfoId(nullable) : 작성자 userInfoId로 검색
     * @return 200,
     *         List<PostViewResponse> posts{
     *             [
     *                Long postId;
     *                Long userInfoId;
     *                String postBody;
     *                Timestamp postTime;
     *                Long categoryId;
     *                String categoryName;
     *                String categoryAbbreviation;
     *                String[] imageUrls;
     *                Boolean isLike;
     *                Boolean isMine;
     *                String userProfileImageUrl;
     *                String userNickname;
     *                Integer likeCount;
     *                Integer commentCount;
     *             ]
     *         }
     *         예시) {"posts":[{"postId":1255,"userInfoId":218,"postBody":"wioeruwoieruoiwuroiweurowejfdlksmjfl;smvkosmdvloksmvolkpsdmvlsdkmvslokkvslkm222222222222222223333333333333333444444444444444444444423534534534456666666","postTime":"2022-02-15T04:47:26.096+00:00","categoryId":1,"categoryName":"jpaTest","categoryAbbreviation":"jpaTest","imageUrls":[],"isLike":false,"isMine":true,"userProfileImageUrl":"http://d17a6yjghl1rix.cloudfront.net/profile_image_preview/6","userNickname":"í","likeCount":1,"commentCount":0}]}
     */
    @Operation(summary = "게시글 조회", description = "Legacy: /board/renderPost")
    @GetMapping(value = "/posts")
    public ResponseEntity<PostViewDTO.PostViewResponseWrapper> getPosts(@ModelAttribute PostViewDTO.PostViewMultipleLookUpRequest postViewMultipleLookUpRequest) {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(lookUpPostViewService.getPostViews(userInfoFromToken, postViewMultipleLookUpRequest));
    }

    /**
     * Legacy: /board/postFromPostId
     * @param postId Long
     * @return 200
     *                Long postId;
     *                Long userInfoId;
     *                String postBody;
     *                Timestamp postTime;
     *                Long categoryId;
     *                String categoryName;
     *                String categoryAbbreviation;
     *                String[] imageUrls;
     *                Boolean isLike;
     *                Boolean isMine;
     *                String userProfileImageUrl;
     *                String userNickname;
     *                Integer likeCount;
     *                Integer commentCount;
     * @throws 404 POST_NOT_FOUND 존재하지 않는 postId
     * @throws 404 POST_DELETED 삭제된 게시글
     * @throws 404 POST_WRITER_BLOCKED 차단한 사용자가 올린 글
     * @throws 403 NOT_CLUB_MEMBER 동아리 회원 아님
     * @throws 403 NOT_SCHOOL_MEMBER 다른 학교 게시글 접근할 때
     * @throws 406 IMAGE_UPLOAD_NOT_COMPLETE 이미지 업로드가 완료되지 않음
     */
    @Operation(summary = "게시글 세부 내용 조회", description = "Legacy: /board/postFromPostId")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 404, message = "1.POST_NOT_FOUND(존재하지 않는 postId)\t\n2.POST_DELETED(삭제된 게시글)\t\n3.POST_WRITER_BLOCKED(차단한 사용자가 올린 글)", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "1.NOT_CLUB_MEMBER(동아리 회원 아님)\t\n2.NOT_SCHOOL_MEMBER(다른 학교 게시글 접근할 때)", response = ErrorResponse.class),
            @ApiResponse(code = 406, message = "1.IMAGE_UPLOAD_NOT_COMPLETE(이미지 업로드가 완료되지 않음)", response = ErrorResponse.class)
    })
    @GetMapping(value = "/posts/{postId}")
    public ResponseEntity<PostViewDTO.PostViewResponse> getPostByPostId(@PathVariable Long postId) {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        PostViewDTO.PostViewSingleLookUpRequest postViewSingleLookUpRequest = 
                PostViewDTO.PostViewSingleLookUpRequest.builder()
                .postId(postId)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(lookUpPostViewService.getPostViewByPostId(userInfoFromToken, postViewSingleLookUpRequest));
    }

}
