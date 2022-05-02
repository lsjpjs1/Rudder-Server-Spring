package com.example.restapimvc.job.command.application;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.exception.CustomException;
import com.example.restapimvc.exception.ErrorCode;
import com.example.restapimvc.job.command.domain.Job;
import com.example.restapimvc.job.command.domain.JobFavorite;
import com.example.restapimvc.job.command.domain.JobFavoriteRepository;
import com.example.restapimvc.job.command.domain.JobRepository;
import com.example.restapimvc.job.command.dto.JobDto;
import com.example.restapimvc.job.query.dto.JobDaoDto;
import com.example.restapimvc.util.mapper.JobMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FavoriteJobService {
    private final JobRepository jobRepository;
    private final JobFavoriteRepository jobFavoriteRepository;
    private final JobMapper jobMapper;

    @Transactional
    public JobDto.FavoriteJobResponse favoriteJob(UserInfo userInfo, JobDto.FavoriteJobRequest favoriteJobRequest) {
        favoriteJobRequest.setAllUserInfo(userInfo);
        Job job = jobRepository.findById(favoriteJobRequest.getJobId())
                .orElseThrow(()->new CustomException(ErrorCode.JOB_NOT_FOUND));
        JobFavorite jobFavorite = JobFavorite.builder()
                .job(job)
                .userInfoId(userInfo.getUserInfoId())
                .build();
        jobFavoriteRepository.save(jobFavorite);
        job.favorite(jobFavorite);
        jobRepository.save(job);
        return jobMapper.entityToFavoriteJobResponse(jobFavorite);
    }
}
