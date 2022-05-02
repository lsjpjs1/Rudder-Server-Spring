package com.example.restapimvc.job.query.application;

import com.example.restapimvc.job.query.dto.JobDaoDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JobDaoQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final QJobDao jobDao = QJobDao.jobDao;

    public List<JobDaoDto.JobDaoResponse> findJobs(JobDaoDto.JobDaoRequest jobDaoRequest) {
        List<JobDaoDto.JobDaoResponse> jobs = jpaQueryFactory
                .select(
                        Projections.constructor(JobDaoDto.JobDaoResponse.class,
                                jobDao.jobId,
                                jobDao.jobTitle,
                                jobDao.jobType,
                                jobDao.companyName,
                                jobDao.salary,
                                jobDao.uploadDate
                        )
                )
                .from(jobDao)
                .limit(20)
                .fetch();
        return jobs;

    }

    public JobDaoDto.JobDaoDetailResponse findJobByJobId(JobDaoDto.JobDaoDetailRequest jobDaoDetailRequest) {
        return jpaQueryFactory
                .select(
                        Projections.constructor(JobDaoDto.JobDaoDetailResponse.class,
                                jobDao.jobId,
                                jobDao.jobTitle,
                                jobDao.jobType,
                                jobDao.companyName,
                                jobDao.salary,
                                jobDao.uploadDate,
                                jobDao.location,
                                jobDao.url,
                                jobDao.expireDate
                        )
                )
                .from(jobDao)
                .where(jobDao.jobId.eq(jobDaoDetailRequest.getJobId()))
                .fetchOne();

    }
}
