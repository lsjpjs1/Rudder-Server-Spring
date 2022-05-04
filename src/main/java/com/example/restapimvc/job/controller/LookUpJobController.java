package com.example.restapimvc.job.controller;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.exception.ErrorResponse;
import com.example.restapimvc.job.query.application.LookUpJobService;
import com.example.restapimvc.job.query.dto.JobDaoDto;
import com.example.restapimvc.post.query.dto.PostViewDTO;
import com.example.restapimvc.security.CustomSecurityContextHolder;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Api( tags = "Job 조회")
public class LookUpJobController {
    private final LookUpJobService lookUpJobService;

    @ApiOperation(value = "job 목록 복수조회")
    @GetMapping(value = "/jobs")
    public ResponseEntity<JobDaoDto.JobDaoResponseWrapper> getJobs(@ModelAttribute JobDaoDto.JobDaoRequest jobDaoRequest) {
        System.out.println(jobDaoRequest.toString());
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(lookUpJobService.getJobs(userInfoFromToken, jobDaoRequest));
    }

    @ApiOperation(value = "내가 즐겨찾기한 job 목록 조회")
    @GetMapping(value = "/jobs/my-favorite")
    public ResponseEntity<JobDaoDto.JobDaoResponseWrapper> getMyFavoriteJobs(@ModelAttribute JobDaoDto.MyFavoriteJobDaoRequest myFavoriteJobDaoRequest) {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(lookUpJobService.getMyFavoriteJobs(userInfoFromToken, myFavoriteJobDaoRequest));
    }

    @ApiOperation(value = "JobId로 조회")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(
                            name = "jobId"
                            , value = "조회할 jobId"
                            , required = true
                            , dataType = "Long"
                            , paramType = "path"
                    )
            })

    @GetMapping(value = "/jobs/{jobId}")
    public ResponseEntity<JobDaoDto.JobDaoDetailResponse> getJobByJobId(@PathVariable Long jobId) {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        JobDaoDto.JobDaoDetailRequest jobDaoDetailRequest = JobDaoDto.JobDaoDetailRequest.builder().jobId(jobId).build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(lookUpJobService.getJobByJobId(userInfoFromToken, jobDaoDetailRequest));
    }
}
