package com.example.restapimvc.job.controller;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.exception.ErrorResponse;
import com.example.restapimvc.job.command.application.FavoriteJobService;
import com.example.restapimvc.job.command.dto.JobDto;
import com.example.restapimvc.job.query.dto.JobDaoDto;
import com.example.restapimvc.post.command.dto.CommonPostDto;
import com.example.restapimvc.post.command.dto.WritePostDto;
import com.example.restapimvc.security.CustomSecurityContextHolder;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Api( tags = "Job 즐겨찾기")
public class FavoriteJobController {
    private final FavoriteJobService favoriteJobService;

    @ApiImplicitParams(
            {
                    @ApiImplicitParam(
                            name = "jobId"
                            , value = "즐겨찾기 추가할 jobId"
                            , required = true
                            , dataType = "Long"
                            , paramType = "path"
                    )
            })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "성공", response = JobDto.FavoriteJobResponse.class),
            @ApiResponse(code = 404, message = "1.JOB_NOT_FOUND(존재하지 않는 jobId) \t\n", response = ErrorResponse.class)
    })
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(value = "/jobs/{jobId}/favorite")
    public ResponseEntity<JobDto.FavoriteJobResponse> addJobFavorite(@PathVariable Long jobId) {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        JobDto.FavoriteJobRequest favoriteJobRequest = JobDto.FavoriteJobRequest.builder().jobId(jobId).build();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(favoriteJobService.favoriteJob(userInfoFromToken, favoriteJobRequest))
                ;
    }

    @ApiImplicitParams(
            {
                    @ApiImplicitParam(
                            name = "jobId"
                            , value = "즐겨찾기 삭제할 jobId"
                            , required = true
                            , dataType = "Long"
                            , paramType = "path"
                    )
            })
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "성공"),
            @ApiResponse(code = 404, message = "1.JOB_NOT_FOUND(존재하지 않는 jobId) \t\n2.JOB_FAVORITE_NOT_FOUND(user가 해당 job을 즐겨찾기한 적이 없음)", response = ErrorResponse.class)
    })
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/jobs/{jobId}/favorite")
    public ResponseEntity deleteJobFavorite(@PathVariable Long jobId) {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        JobDto.FavoriteJobRequest favoriteJobRequest = JobDto.FavoriteJobRequest.builder().jobId(jobId).build();
        favoriteJobService.deleteFavoriteJob(userInfoFromToken, favoriteJobRequest);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build()
                ;
    }
}
