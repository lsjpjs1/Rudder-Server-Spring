package com.example.restapimvc.post.query.application;

import com.example.restapimvc.domain.*;
import com.example.restapimvc.post.query.dto.PostViewDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostViewQueryRepository {

    @Value("${cloud-front.url.profile-image-preview}")
    private String CLOUD_FRONT_PROFILE_IMAGE_PREVIEW_URL;

    private final JPAQueryFactory jpaQueryFactory;
    private final QPostView postView = QPostView.postView;
    private final QCategory category = QCategory.category;
    private final QUserInfo userInfo = QUserInfo.userInfo;
    private final QUserProfile userProfile = QUserProfile.userProfile;
    private final QPostImage postImage = QPostImage.postImage;
    private final QPostLike postLike = QPostLike.postLike;
    private final QUserBlock userBlock = QUserBlock.userBlock;
    private final QClubMember clubMember = QClubMember.clubMember;


    public List<PostViewDTO.PostViewResponse> findPosts(PostViewDTO.PostViewRequest postViewRequest) {

        List<PostViewDTO.PostViewResponse> results = jpaQueryFactory
                .select(
                        Projections.constructor(PostViewDTO.PostViewResponse.class,
                                postView.postId,
                                userInfo.userInfoId.max(),
                                postView.postBody.max(),
                                postView.postTime,
                                postView.categoryId.max(),
                                category.categoryName.max(),
                                category.categoryAbbreviation.max(),
                                Expressions.stringTemplate("string_agg({0},{1})", postImage.fileName, ","),
                                new CaseBuilder()
                                        .when(postLike.userId.eq(postViewRequest.getUserId())).then(1)
                                        .otherwise(0)
                                        .max()
                                        .eq(1),
                                new CaseBuilder()
                                        .when(userInfo.userInfoId.eq(postViewRequest.getUserInfoId())).then(1)
                                        .otherwise(0)
                                        .max()
                                        .eq(1),
                                userProfile.profileImageId.max().stringValue().prepend(CLOUD_FRONT_PROFILE_IMAGE_PREVIEW_URL),
                                userInfo.userNickname.max(),
                                postView.likeCount.max(),
                                postView.commentCount.max()

                        )
                )
                .from(postView)
                .leftJoin(category).on(postView.categoryId.eq(category.categoryId))
                .leftJoin(userInfo).on(postView.userId.eq(userInfo.userId))
                .leftJoin(userProfile).on(userInfo.userProfile.profileId.eq(userProfile.profileId))
                .leftJoin(postImage).on(postView.postId.eq(postImage.postId))
                .leftJoin(postLike).on(postView.postId.eq(postLike.postId))
                .leftJoin(userBlock).on(userInfo.userInfoId.eq(userBlock.blockedUserInfo.userInfoId))
                .where(
                        postView.schoolId.eq(postViewRequest.getSchoolId())
                                .and(lessThanPostId(postViewRequest.getEndPostId()))
                                .and(equalCategoryId(postViewRequest.getCategoryId()))
                                .and(searchFromPostBody(postViewRequest.getSearchBody()))
                                .and(postView.isDelete.isFalse())
                                .and(userBlock.userInfo.userInfoId.eq(postViewRequest.getUserInfoId()).not())
                                .and(
                                        new CaseBuilder()
                                                .when(category.categoryType.eq("club").not()).then(Boolean.TRUE)
                                                .otherwise(
                                                        new CaseBuilder()
                                                                .when(JPAExpressions.select(clubMember.clubMemberId.count())
                                                                        .from(clubMember)
                                                                        .where(
                                                                                clubMember.categoryId.eq(category.categoryId)
                                                                                        .and(clubMember.userInfoId.eq(postViewRequest.getUserInfoId()))
                                                                        )
                                                                        .gt(0l)).then(Boolean.TRUE)
                                                                .otherwise(Boolean.FALSE)
                                                )
                                                .eq(Boolean.TRUE)
                                )

                )
                .groupBy(postView.postId, postView.postTime)
                .orderBy(postView.postTime.desc())
                .limit(20)
                .fetch();

        PostViewDTO.PostViewResponse.fillEmptyStringInImageUrls(results);
        return results;
    }


    private BooleanExpression lessThanPostId(Long endPostId) {
        if (endPostId == null) {
            return null; // BooleanExpression 자리에 null이 반환되면 조건문에서 자동으로 제거된다
        }
        return postView.postId.lt(endPostId);
    }

    private BooleanExpression equalCategoryId(Long categoryId) {
        if (categoryId == null) {
            return null;
        }
        return category.categoryId.eq(categoryId);
    }

    private BooleanExpression searchFromPostBody(String searchBody) {
        if (searchBody == null) {
            return null;
        }
        return postView.postBody.contains(searchBody);
    }
}
