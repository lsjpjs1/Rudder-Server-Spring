package com.example.restapimvc.job.query.application;

import com.example.restapimvc.post.query.application.PostView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface JobDaoRepository extends JpaRepository<JobDao, Long> {
    Optional<JobDao> findByCompanyNameAndJobTitle(String companyName, String jobTitle);
}