package com.example.restapimvc.repository;

import com.example.restapimvc.domain.QCategory;
import com.example.restapimvc.domain.QUserSelectCategory;
import com.example.restapimvc.dto.CategoryDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final QCategory category = QCategory.category;
    private final QUserSelectCategory userSelectCategory = QUserSelectCategory.userSelectCategory;

    public List<CategoryDTO.CommonCategoryResponse> findCommonCategory(long userInfoId, long schoolId) {

        return jpaQueryFactory
                .select(
                        Projections.constructor(CategoryDTO.CommonCategoryResponse.class,
                                category.categoryId,
                                category.categoryName,
                                JPAExpressions
                                        .select(userSelectCategory)
                                        .from(userSelectCategory)
                                        .where(userSelectCategory.userInfo.userInfoId.eq(userInfoId)
                                                .and(userSelectCategory.category.categoryId.eq(category.categoryId)))
                                        .exists()
                                        .as("isSelect")
                        )
                )
                .from(category)
                .where(category.school.schoolId.eq(schoolId).and(category.categoryEnable.eq(true)))
                .orderBy(category.categoryOrder.asc())
                .fetch();

    }

}
