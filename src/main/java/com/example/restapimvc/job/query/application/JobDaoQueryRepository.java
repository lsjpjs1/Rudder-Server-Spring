package com.example.restapimvc.job.query.application;

import com.example.restapimvc.job.command.domain.QJobFavorite;
import com.example.restapimvc.job.query.dto.JobDaoDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JobDaoQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final QJobDao jobDao = QJobDao.jobDao;
    private final QJobFavorite jobFavorite = QJobFavorite.jobFavorite;

    public List<JobDaoDto.JobDaoSummaryResponse> findJobs(JobDaoDto.JobDaoRequest jobDaoRequest) {
        List<JobDaoDto.JobDaoSummaryResponse> jobs = jpaQueryFactory
                .select(
                        Projections.constructor(JobDaoDto.JobDaoSummaryResponse.class,
                                jobDao.jobId,
                                jobDao.jobTitle,
                                jobDao.jobType,
                                jobDao.companyName,
                                jobDao.salary,
                                jobDao.uploadDate,
                                new CaseBuilder()
                                        .when(jobFavorite.userInfoId.eq(jobDaoRequest.getUserInfoId())).then(1)
                                        .otherwise(0)
                                        .max()
                                        .eq(1)
                        )
                )
                .from(jobDao)
                .leftJoin(jobFavorite).on(jobDao.jobId.eq(jobFavorite.job.jobId))
                .where(
                        lessThanJobId(jobDaoRequest.getEndJobId())
                                .and(searchFromJobSummary(jobDaoRequest.getSearchBody()))
                )
                .groupBy(jobDao.jobId)
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
                                jobDao.expireDate,
                                new CaseBuilder()
                                        .when(jobFavorite.userInfoId.eq(jobDaoDetailRequest.getUserInfoId())).then(1)
                                        .otherwise(0)
                                        .max()
                                        .eq(1)
                        )
                )
                .from(jobDao)
                .leftJoin(jobFavorite).on(jobDao.jobId.eq(jobFavorite.job.jobId))
                .where(jobDao.jobId.eq(jobDaoDetailRequest.getJobId()))
                .groupBy(jobDao.jobId)
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
