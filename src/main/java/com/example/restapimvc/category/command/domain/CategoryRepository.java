package com.example.restapimvc.category.command.domain;

import com.example.restapimvc.job.command.domain.JobFavorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
