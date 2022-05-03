package com.example.restapimvc.job.query.application;

import com.example.restapimvc.job.command.domain.Job;
import com.example.restapimvc.job.query.dto.JobDaoDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
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
                .where(
                        lessThanJobId(jobDaoRequest.getEndJobId())
                                .and(searchFromJobSummary(jobDaoRequest.getSearchBody()))
                )
                .from(jobDao)
                .orderBy(jobDao.jobId.desc())
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

    private BooleanExpression searchFromJobSummary(String searchBody) {
        if (searchBody == null) {
            return null;
        }
        return jobDao.searchSummary.contains(searchBody);
    }

    private BooleanExpression lessThanJobId(Long endJobId) {
        if (endJobId == null) {
            return null; // BooleanExpression 자리에 null이 반환되면 조건문에서 자동으로 제거된다
        }
        return jobDao.jobId.lt(endJobId);
    }

}
