package com.example.restapimvc.job.controller;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.job.query.application.LookUpJobService;
import com.example.restapimvc.job.query.dto.JobDaoDto;
import com.example.restapimvc.post.query.dto.PostViewDTO;
import com.example.restapimvc.security.CustomSecurityContextHolder;
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
public class LookUpJobController {
    private final LookUpJobService lookUpJobService;

    @GetMapping(value = "/jobs")
    public ResponseEntity<JobDaoDto.JobDaoResponseWrapper> getJobs(@ModelAttribute JobDaoDto.JobDaoRequest jobDaoRequest) {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(lookUpJobService.getJobs(userInfoFromToken, jobDaoRequest));
    }

    @GetMapping(value = "/jobs/my-favorite")
    public ResponseEntity<JobDaoDto.JobDaoResponseWrapper> getMyFavoriteJobs(@ModelAttribute JobDaoDto.MyFavoriteJobDaoRequest myFavoriteJobDaoRequest) {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(lookUpJobService.getMyFavoriteJobs(userInfoFromToken, myFavoriteJobDaoRequest));
    }

    @GetMapping(value = "/jobs/{jobId}")
    public ResponseEntity<JobDaoDto.JobDaoDetailResponse> getJobByJobId(@PathVariable Long jobId) {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        JobDaoDto.JobDaoDetailRequest jobDaoDetailRequest = JobDaoDto.JobDaoDetailRequest.builder().jobId(jobId).build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(lookUpJobService.getJobByJobId(userInfoFromToken, jobDaoDetailRequest));
    }
}
