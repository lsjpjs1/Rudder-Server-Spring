package com.example.restapimvc.category.command.domain;

import com.example.restapimvc.category.command.dto.CategoryDto;
import com.example.restapimvc.domain.QSchool;
import com.example.restapimvc.domain.QUserSelectCategory;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
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

    public List<CategoryDto.CategoryResponse> findCategories(CategoryDto.GetCategoriesRequest getCategoriesRequest) {
        return jpaQueryFactory
                .select(
                        Projections.constructor(CategoryDto.CategoryResponse.class,
                                category.categoryId,
                                category.categoryType.max(),
                                category.categoryName.max(),
                                category.categoryAbbreviation.max()
                        )
                )
                .from(category)
                .leftJoin(userSelectCategory).on(category.categoryId.eq(userSelectCategory.categoryDao.categoryId))
                .where(
                        category.school.schoolId.eq(getCategoriesRequest.getSchoolId()),
                        category.categoryEnable.eq(Boolean.TRUE),
                        isUserSelectCategory(getCategoriesRequest),
                        equalCategoryType(getCategoriesRequest.getCategoryTypes())
                )
                .groupBy(category.categoryId, category.categoryOrder)
                .orderBy(category.categoryOrder.asc())
                .fetch();
    }

    private BooleanExpression isUserSelectCategory(CategoryDto.GetCategoriesRequest getCategoriesRequest) {
        if (getCategoriesRequest.getIsUserSelectCategory() == null) {
            return null;
        }
        return new CaseBuilder()
                .when(JPAExpressions.select(userSelectCategory.userSelectCategoryId.count())
                        .from(userSelectCategory)
                        .where(
                                userSelectCategory.categoryDao.categoryId.eq(category.categoryId)
                                        .and(userSelectCategory.userInfo.userInfoId.eq(getCategoriesRequest.getUserInfoId()))
                        )
                        .gt(0l)).then(Boolean.TRUE)
                .otherwise(Boolean.FALSE)
                .eq(Boolean.TRUE);
    }

    private BooleanExpression equalCategoryType(List<String> categoryTypes) {
        if (categoryTypes == null || categoryTypes.isEmpty()) {
            return null;
        }
        return category.categoryType.in(categoryTypes);
    }
}
