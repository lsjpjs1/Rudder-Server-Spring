package com.example.restapimvc.job.command.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JobFavoriteRepository extends JpaRepository<JobFavorite, Long> {
}
