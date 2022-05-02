package com.example.restapimvc.job.insert.application;

import com.example.restapimvc.job.query.application.JobDao;
import com.example.restapimvc.job.query.application.JobDaoRepository;
import com.example.restapimvc.scraper.common.FindTarget;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class InsertJobService {
    private final JobDaoRepository jobDaoRepository;


    public void insertJobs(Collection<FindTarget> findTargets) {
        for (FindTarget findTarget :
                findTargets) {
            insertJob(findTarget);
        }
    }

    @Transactional
    public void insertJob(FindTarget findTarget) {
        if (findTarget.getJobTitle()==null || findTarget.getCompanyName()==null) {
            return;
        }
        if (jobDaoRepository.findByCompanyNameAndJobTitle(findTarget.getCompanyName(), findTarget.getJobTitle()).isPresent()) {
            return;
        }
        JobDao jobDao = JobDao.from(findTarget);
        jobDaoRepository.save(jobDao);
    }
}
