package com.example.restapimvc.post.controller;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.dto.UserInfoDto;
import com.example.restapimvc.post.query.application.PostView;
import com.example.restapimvc.post.query.application.PostViewQueryRepository;
import com.example.restapimvc.post.query.application.PostViewService;
import com.example.restapimvc.post.query.dto.PostViewDTO;
import com.example.restapimvc.security.CustomSecurityContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostViewService postViewService;


    /**
     * @param postViewRequest
     *          Long endPostId(nullable)
     *          Long categoryId(nullable)
     *          String searchBody(nullable)
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
    @GetMapping(value = "/posts")
    public ResponseEntity<PostViewDTO.PostViewResponseWrapper> getPosts(@ModelAttribute PostViewDTO.PostViewRequest postViewRequest) {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(postViewService.getPostViews(userInfoFromToken, postViewRequest));
    }
}
