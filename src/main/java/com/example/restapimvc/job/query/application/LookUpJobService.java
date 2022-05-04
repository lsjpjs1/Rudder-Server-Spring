package com.example.restapimvc.job.query.application;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.exception.CustomException;
import com.example.restapimvc.exception.ErrorCode;
import com.example.restapimvc.job.query.dto.JobDaoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LookUpJobService {
    private final JobDaoRepository jobDaoRepository;
    private final JobDaoQueryRepository jobDaoQueryRepository;

    @Transactional
    public JobDaoDto.JobDaoResponseWrapper getJobs(UserInfo userInfo, JobDaoDto.JobDaoRequest jobDaoRequest) {
        jobDaoRequest.setAllUserInfo(userInfo);
        return new JobDaoDto.JobDaoResponseWrapper(jobDaoQueryRepository.findJobs(jobDaoRequest));

    }

    @Transactional
    public JobDaoDto.JobDaoResponseWrapper getMyFavoriteJobs(UserInfo userInfo, JobDaoDto.MyFavoriteJobDaoRequest myFavoriteJobDaoRequest) {
        myFavoriteJobDaoRequest.setAllUserInfo(userInfo);
        return new JobDaoDto.JobDaoResponseWrapper(jobDaoQueryRepository.findMyFavoriteJobs(myFavoriteJobDaoRequest));

    }

    @Transactional
    public JobDaoDto.JobDaoDetailResponse getJobByJobId(UserInfo userInfo, JobDaoDto.JobDaoDetailRequest jobDaoDetailRequest) {
        jobDaoRepository.findById(jobDaoDetailRequest.getJobId())
                .orElseThrow(()->new CustomException(ErrorCode.JOB_NOT_FOUND));
        jobDaoDetailRequest.setAllUserInfo(userInfo);
        return jobDaoQueryRepository.findJobByJobId(jobDaoDetailRequest);

    }
}
