package com.example.restapimvc.category.command.domain;

import com.example.restapimvc.category.command.dto.CategoryDto;
import com.example.restapimvc.domain.QCategoryJoinRequest;
import com.example.restapimvc.domain.QClubMember;
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
    private final QCategoryJoinRequest categoryJoinRequest = QCategoryJoinRequest.categoryJoinRequest;
    private final QClubMember clubMember = QClubMember.clubMember;

    public List<CategoryDto.CategoryResponse> findCategories(CategoryDto.GetCategoriesRequest getCategoriesRequest) {
        return jpaQueryFactory
                .select(
                        Projections.constructor(CategoryDto.CategoryResponse.class,
                                category.categoryId,
                                category.categoryType.max(),
                                category.categoryName.max(),
                                category.categoryAbbreviation.max(),
                                new CaseBuilder()
                                        .when(userSelectCategory.userInfo.userInfoId.eq(getCategoriesRequest.getUserInfoId())).then(1)
                                        .otherwise(0)
                                        .max()
                                        .eq(1),

                                new CaseBuilder()
                                        .when(isClubMember(getCategoriesRequest)).then("t")
                                        .otherwise(
                                                new CaseBuilder()
                                                        .when(isClubJoinPending(getCategoriesRequest)).then("r")
                                                        .otherwise("f")
                                        )
                        )
                )
                .from(category)
                .leftJoin(userSelectCategory).on(category.categoryId.eq(userSelectCategory.categoryId))
                .leftJoin(categoryJoinRequest).on(category.categoryId.eq(categoryJoinRequest.categoryId))
                .leftJoin(clubMember).on(category.categoryId.eq(clubMember.categoryId))
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

    private BooleanExpression isClubMember(CategoryDto.GetCategoriesRequest getCategoriesRequest) {
        return new CaseBuilder()
                .when(categoryJoinRequest.userInfoId.eq(getCategoriesRequest.getUserInfoId())).then(1)
                .otherwise(0)
                .max()
                .eq(1);
    }

    private BooleanExpression isClubJoinPending(CategoryDto.GetCategoriesRequest getCategoriesRequest) {
        return new CaseBuilder()
                .when(clubMember.userInfoId.eq(getCategoriesRequest.getUserInfoId())).then(1)
                .otherwise(0)
                .max()
                .eq(1);
    }

    private BooleanExpression isUserSelectCategory(CategoryDto.GetCategoriesRequest getCategoriesRequest) {
        if (getCategoriesRequest.getIsUserSelectCategory() == null) {
            return null;
        }
        return new CaseBuilder()
                .when(JPAExpressions.select(userSelectCategory.userSelectCategoryId.count())
                        .from(userSelectCategory)
                        .where(
                                userSelectCategory.categoryId.eq(category.categoryId)
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
