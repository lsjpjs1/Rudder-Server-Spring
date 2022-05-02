package com.example.restapimvc.repository;

import com.example.restapimvc.dao.QCategoryDao;
import com.example.restapimvc.domain.QUserSelectCategory;
import com.example.restapimvc.domain.School;
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
    private final QCategoryDao category = QCategoryDao.categoryDao;
    private final QUserSelectCategory userSelectCategory = QUserSelectCategory.userSelectCategory;

    public List<CategoryDTO.CommonCategoryResponse> findCommonCategory(long userInfoId, School school) {

        return jpaQueryFactory
                .select(
                        Projections.constructor(CategoryDTO.CommonCategoryResponse.class,
                                category.categoryId,
                                category.categoryName,
                                JPAExpressions
                                        .select(userSelectCategory)
                                        .from(userSelectCategory)
                                        .where(userSelectCategory.userInfo.userInfoId.eq(userInfoId)
                                                .and(userSelectCategory.categoryDao.categoryId.eq(category.categoryId)))
                                        .exists()
                                        .as("isSelect")
                        )
                )
                .from(category)
                .where(category.school.schoolId.eq(school.getSchoolId()).and(category.categoryEnable.eq(true)))
                .orderBy(category.categoryOrder.asc())
                .fetch();

    }

}
