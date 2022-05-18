package com.example.restapimvc.post.controller;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.exception.ErrorResponse;
import com.example.restapimvc.post.command.application.PostImageUploadService;
import com.example.restapimvc.post.command.application.WritePostService;
import com.example.restapimvc.post.command.dto.CommonPostDto;
import com.example.restapimvc.post.command.dto.FileDto;
import com.example.restapimvc.post.command.dto.WritePostDto;
import com.example.restapimvc.security.CustomSecurityContextHolder;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
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
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "게시글 작성", description = "Legacy: /board/addPost")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "성공"),
            @ApiResponse(code = 406, message = "1.BAD_REQUEST_CONTENT(파라미터로 null값 넘어올 때)", response = ErrorResponse.class)
    })
    @PostMapping(value = "/posts")
    public ResponseEntity<CommonPostDto.CommonPostResponse> writePost(@RequestBody WritePostDto.WritePostRequest writePostRequest) {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(writePostService.writePost(userInfoFromToken,writePostRequest))
                ;
    }


    /**
     * Legacy: /board/getUploadSignedUrls 기존에 한번에 처리되는 로직 두개로 분리함, 프론트에서 이미지 업로드가 완료되면 "/posts/{postId}/image" api를 호츨해야함
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
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "S3용 업로드용 signedUrl 가져오기", description = "기egacy: /board/getUploadSignedUrls 기존에 한번에 처리되는 로직 두개로 분리함, 프론트에서 이미지 업로드가 완료되면 \"/posts/{postId}/image\" api를 호츨해야함")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "성공"),
            @ApiResponse(code = 406, message = "1.BAD_REQUEST_CONTENT(imageMetaData가 빈 배열이거나 Null일때)", response = ErrorResponse.class)
    })
    @PostMapping(value = "/posts/image-upload-url/generate")
    public ResponseEntity<FileDto.UploadUrlsWrapper> getS3SignedUrl(@RequestBody WritePostDto.ImageUploadUrlRequest imageUploadUrlRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(postImageUploadService.getImageUploadUrl(imageUploadUrlRequest))
                ;
    }

    /**
     * Legacy: /board/getUploadSignedUrls 기존에 한번에 처리되는 로직 두개로 분리함, 프론트에서 이미지 업로드가 완료되면 이 api를 호츨해야함
     * @param postId Long
     * @param completeImageUploadRequest
     *          List fileNames[
     *              String...
     *          ]
     *          ex) {"fileNames":["file1","file2","file3"]}
     * @return 201
     *          Long postId
     *          List postImages[
     *              Long postImageId
     *              String fileName
     *          ]
     *          ex) {"postId":1485,"postImages":[{"postImageId":337,"fileName":"file1"},{"postImageId":339,"fileName":"file3"},{"postImageId":338,"fileName":"file2"}]}
     * @throws 406 BAD_REQUEST_CONTENT null이나 빈 fileNames
     * @throws 404 POST_NOT_FOUND 존재하지 않는 postId
     * @throws 403 NO_PERMISSION 내가 쓴 글이 아님
     */
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "프론트에서 이미지 업로드 완료됐다고 알려줌", description = "Legacy: /board/getUploadSignedUrls 기존에 한번에 처리되는 로직 두개로 분리함, 프론트에서 이미지 업로드가 완료되면 이 api를 호츨해야함")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "성공"),
            @ApiResponse(code = 404, message = "1.POST_NOT_FOUND(존재하지 않는 postId)", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "1.NO_PERMISSION(내가 쓴 글이 아님)", response = ErrorResponse.class),
            @ApiResponse(code = 406, message = "1.BAD_REQUEST_CONTENT(null이나 빈 fileNames)", response = ErrorResponse.class)
    })
    @PostMapping(value = "/posts/{postId}/image")
    public ResponseEntity<WritePostDto.CompleteImageUploadResponse> completeImageUpload(@PathVariable Long postId, @RequestBody WritePostDto.CompleteImageUploadRequest completeImageUploadRequest) {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        completeImageUploadRequest.setPostId(postId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(postImageUploadService.completeImageUpload(userInfoFromToken,completeImageUploadRequest))
                ;
    }

}
