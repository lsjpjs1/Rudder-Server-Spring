package com.example.restapimvc.job.controller;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.job.command.application.FavoriteJobService;
import com.example.restapimvc.job.command.dto.JobDto;
import com.example.restapimvc.post.command.dto.CommonPostDto;
import com.example.restapimvc.post.command.dto.WritePostDto;
import com.example.restapimvc.security.CustomSecurityContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FavoriteJobController {
    private final FavoriteJobService favoriteJobService;

    @PostMapping(value = "/jobs/{jobId}/favorite")
    public ResponseEntity<JobDto.FavoriteJobResponse> writePost(@PathVariable Long jobId) {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        JobDto.FavoriteJobRequest favoriteJobRequest = JobDto.FavoriteJobRequest.builder().jobId(jobId).build();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(favoriteJobService.favoriteJob(userInfoFromToken, favoriteJobRequest))
                ;
    }
}
