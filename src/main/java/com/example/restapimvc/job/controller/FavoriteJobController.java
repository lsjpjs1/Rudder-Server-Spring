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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
            @ApiResponse(code = 201, message = "성공", response = JobDaoDto.JobDaoDetailResponse.class),
            @ApiResponse(code = 400, message = "1.등록되지 않은 개인 미션 \t\n 2.등록되지 않은 유저 \t\n 3.하루 인증 횟수 초과", response = ErrorResponse.class)
    })
    @PostMapping(value = "/jobs/{jobId}/favorite")
    public ResponseEntity<JobDto.FavoriteJobResponse> addJobFavorite(@PathVariable Long jobId) {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        JobDto.FavoriteJobRequest favoriteJobRequest = JobDto.FavoriteJobRequest.builder().jobId(jobId).build();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(favoriteJobService.favoriteJob(userInfoFromToken, favoriteJobRequest))
                ;
    }
}
