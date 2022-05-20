package com.example.restapimvc.category.command.domain;

import com.example.restapimvc.job.command.domain.JobFavorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findTopByCategoryEnableAndCategoryTypeOrderByCategoryOrderAsc(Boolean categoryEnable, String categoryType);
}
