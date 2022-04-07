package com.example.restapimvc.post.controller;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.post.command.application.PostImageUploadService;
import com.example.restapimvc.post.command.application.WritePostService;
import com.example.restapimvc.post.command.dto.CommonPostDto;
import com.example.restapimvc.post.command.dto.FileDto;
import com.example.restapimvc.post.command.dto.WritePostDto;
import com.example.restapimvc.security.CustomSecurityContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class WritePostController {

    private final WritePostService writePostService;
    private final PostImageUploadService postImageUploadService;

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
    public ResponseEntity<CommonPostDto.CommonPostResponse> writePost(@RequestBody WritePostDto.WritePostRequest writePostRequest) {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(writePostService.writePost(userInfoFromToken,writePostRequest))
                ;
    }


    /**
     * /board/getUploadSignedUrls
     * @param imageUploadUrlRequest
     *          List imageMetaData[
     *              String fileName 파일 이름
     *              String contentType 컨텐트 타입
     *          ]
     *          ex) {"imageMetaData":[{"fileName":"one","contentType":"image/jpeg"},{"fileName":"two","contentType":"image/png"}]}
     * @return 201
     *          List uploadUrls[
     *              String url
     *          ]
     *          ex) {"uploadUrls":[{"url":"https://rudder-test-image-bucket.s3.ap-northeast-2.amazonaws.com/one?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20220407T125354Z&X-Amz-SignedHeaders=content-type%3Bhost&X-Amz-Expires=180&X-Amz-Credential=AKIAUYFEIVHCEHHLJQPL%2F20220407%2Fap-northeast-2%2Fs3%2Faws4_request&X-Amz-Signature=0fb15268aba383b52ff19d1beb0021b295e455139ba2c5565a9374a975cb257c"},{"url":"https://rudder-test-image-bucket.s3.ap-northeast-2.amazonaws.com/two?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20220407T125354Z&X-Amz-SignedHeaders=content-type%3Bhost&X-Amz-Expires=180&X-Amz-Credential=AKIAUYFEIVHCEHHLJQPL%2F20220407%2Fap-northeast-2%2Fs3%2Faws4_request&X-Amz-Signature=57552edde1c522647c6fc04364e1635a04dd002b50e9224741ab0cf445530e19"}]}
     * @throws 406 BAD_REQUEST_CONTENT imageMetaData가 빈 배열이거나 Null일때
     */
    @PostMapping(value = "/posts/image-upload-url/generate")
    public ResponseEntity<FileDto.UploadUrlsWrapper> getS3SignedUrl(@RequestBody WritePostDto.ImageUploadUrlRequest imageUploadUrlRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(postImageUploadService.getImageUploadUrl(imageUploadUrlRequest))
                ;
    }

}
