package com.example.restapimvc.job.command.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobFavoriteRepository extends JpaRepository<JobFavorite, Long> {
    Optional<JobFavorite> findByJob(Job job);
    Optional<JobFavorite> findByJobAndUserInfoId(Job job,Long userInfoId);
}
