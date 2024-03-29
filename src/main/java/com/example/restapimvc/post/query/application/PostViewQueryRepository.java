package com.example.restapimvc.post.query.application;


import com.example.restapimvc.comment.command.domain.QComment;
import com.example.restapimvc.common.WithUserInfo;
import com.example.restapimvc.dao.QCategoryDao;
import com.example.restapimvc.domain.*;
import com.example.restapimvc.post.command.domain.QPostImage;
import com.example.restapimvc.post.command.domain.QPostLike;
import com.example.restapimvc.post.query.dto.PostViewDTO;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class PostViewQueryRepository {

    @Value("${cloud-front.url.profile-image-preview}")
    private String CLOUD_FRONT_PROFILE_IMAGE_PREVIEW_URL;




    private final JPAQueryFactory jpaQueryFactory;
    private final QPostView postView = QPostView.postView;
    private final QCategoryDao category = QCategoryDao.categoryDao;
    private final QUserInfo userInfo = QUserInfo.userInfo;
    private final QUserProfile userProfile = QUserProfile.userProfile;
    private final QPostImage postImage = QPostImage.postImage;
    private final QPostLike postLike = QPostLike.postLike;
    private final QUserBlock userBlock = QUserBlock.userBlock;
    private final QClubMember clubMember = QClubMember.clubMember;
    private final QComment comment =QComment.comment;


    public List<PostViewDTO.PostViewResponse> findPosts(PostViewDTO.PostViewMultipleLookUpRequest postViewMultipleLookUpRequest) {
        log.info(postViewMultipleLookUpRequest.toString());
        List<PostViewDTO.PostViewResponse> results = getPostViewResponseJPAQueryUntilJoin(getPostViewResponseConstructorExpression(postViewMultipleLookUpRequest))
                .where(
                        postView.schoolId.eq(postViewMultipleLookUpRequest.getSchoolId())
                                .and(postView.isDelete.isFalse())
                                .and(postView.isImageUploading.isFalse())
                                .and(
                                        new CaseBuilder()
                                                .when(category.categoryType.eq("club").not()).then(Boolean.TRUE)
                                                .otherwise(
                                                        new CaseBuilder()
                                                                .when(JPAExpressions.select(clubMember.clubMemberId.count())
                                                                        .from(clubMember)
                                                                        .where(
                                                                                clubMember.categoryId.eq(category.categoryId)
                                                                                        .and(clubMember.userInfoId.eq(postViewMultipleLookUpRequest.getUserInfoId()))
                                                                        )
                                                                        .gt(0l)).then(Boolean.TRUE)
                                                                .otherwise(Boolean.FALSE)
                                                )
                                                .eq(Boolean.TRUE)
                                )
                                .and(lessThanPostId(postViewMultipleLookUpRequest.getEndPostId()))
                                .and(equalCategoryId(postViewMultipleLookUpRequest.getCategoryId()))
                                .and(searchFromPostBody(postViewMultipleLookUpRequest.getSearchBody()))
                                .and(isMyPost(postViewMultipleLookUpRequest.getIsMyPost(), postViewMultipleLookUpRequest.getUserInfoId()))
                )
                .groupBy(postView.postId, postView.postTime)
                .having(
                        new CaseBuilder()
                                .when(userBlock.userInfo.userInfoId.eq(postViewMultipleLookUpRequest.getUserInfoId())).then(1)
                                .otherwise(0)
                                .sum()
                                .eq(0)
                )
                .orderBy(postView.postTime.desc())
                .limit(20)
                .fetch();

        PostViewDTO.PostViewResponse.setImageUrlsNonNull(results);
        return results;
    }

public List<PostViewDTO.PostViewResponse> findPostsWithMyComment(PostViewDTO.PostViewMultipleLookUpWithMyCommentRequest postViewMultipleLookUpWithMyCommentRequest) {
        List<PostViewDTO.PostViewResponse> results = getPostViewWithMyCommentResponseJPAQueryUntilJoin(getPostViewResponseConstructorExpression(postViewMultipleLookUpWithMyCommentRequest))
                .where(
                        postView.schoolId.eq(postViewMultipleLookUpWithMyCommentRequest.getSchoolId())
                                .and(postView.isDelete.isFalse())
                                .and(postView.isImageUploading.isFalse())
                                .and(
                                        new CaseBuilder()
                                                .when(category.categoryType.eq("club").not()).then(Boolean.TRUE)
                                                .otherwise(
                                                        new CaseBuilder()
                                                                .when(JPAExpressions.select(clubMember.clubMemberId.count())
                                                                        .from(clubMember)
                                                                        .where(
                                                                                clubMember.categoryId.eq(category.categoryId)
                                                                                        .and(clubMember.userInfoId.eq(postViewMultipleLookUpWithMyCommentRequest.getUserInfoId()))
                                                                        )
                                                                        .gt(0l)).then(Boolean.TRUE)
                                                                .otherwise(Boolean.FALSE)
                                                )
                                                .eq(Boolean.TRUE)
                                )
                                .and(lessThanPostId(postViewMultipleLookUpWithMyCommentRequest.getEndPostId()))
                                .and(isMyComment(postViewMultipleLookUpWithMyCommentRequest.getUserInfoId()))
                )
                .groupBy(postView.postId, postView.postTime)
                .having(
                        new CaseBuilder()
                                .when(userBlock.userInfo.userInfoId.eq(postViewMultipleLookUpWithMyCommentRequest.getUserInfoId())).then(1)
                                .otherwise(0)
                                .sum()
                                .eq(0)
                )
                .orderBy(postView.postTime.desc())
                .limit(20)
                .fetch();

        PostViewDTO.PostViewResponse.setImageUrlsNonNull(results);
        return results;
    }


    public PostViewDTO.PostViewResponse findPostByPostId(PostViewDTO.PostViewSingleLookUpRequest postViewSingleLookUpRequest) {

        PostViewDTO.PostViewResponse result = getPostViewResponseJPAQueryUntilJoin(getPostViewResponseConstructorExpression(postViewSingleLookUpRequest))
                .where(
                        postView.postId.eq(postViewSingleLookUpRequest.getPostId())
                )
                .groupBy(postView.postId, postView.postTime)
                .fetchOne();

        PostViewDTO.PostViewResponse.setImageUrlsNonNull(result);
        return result;
    }


    public Optional<PostView> findByPostIdAndBlock(PostViewDTO.PostViewSingleLookUpRequest postViewSingleLookUpRequest) {
        PostView result = jpaQueryFactory.select(this.postView)
                .from(this.postView)
                .leftJoin(userInfo).on(this.postView.userId.eq(userInfo.userId))
                .leftJoin(userBlock).on(userInfo.userInfoId.eq(userBlock.blockedUserInfo.userInfoId))
                .where(
                        this.postView.postId.eq(postViewSingleLookUpRequest.getPostId())
                                .and(userBlock.userInfo.userInfoId.eq(postViewSingleLookUpRequest.getUserInfoId()))
                )
                .fetchOne();

        return Optional.ofNullable(result);

    }

    public Optional<PostView> findByPostIdAndClubMember(PostViewDTO.PostViewSingleLookUpRequest postViewSingleLookUpRequest) {
        PostView result = jpaQueryFactory.select(this.postView)
                .from(this.postView)
                .leftJoin(category).on(postView.categoryId.eq(category.categoryId))
                .leftJoin(clubMember).on(postView.categoryId.eq(clubMember.categoryId))
                .where(
                        this.postView.postId.eq(postViewSingleLookUpRequest.getPostId())
                                .and(
                                        category.categoryType.eq("club").not()
                                                .or(clubMember.clubMemberId.eq(postViewSingleLookUpRequest.getUserInfoId()))
                                )
                )
                .fetchOne();

        return Optional.ofNullable(result);

    }

    private JPAQuery<PostViewDTO.PostViewResponse> getPostViewResponseJPAQueryUntilJoin(ConstructorExpression<PostViewDTO.PostViewResponse> postViewResponseConstructorExpression) {
        return jpaQueryFactory
                .select(
                        postViewResponseConstructorExpression
                )
                .from(postView)
                .leftJoin(category).on(postView.categoryId.eq(category.categoryId))
                .leftJoin(userInfo).on(postView.userId.eq(userInfo.userId))
                .leftJoin(userProfile).on(userInfo.userProfile.profileId.eq(userProfile.profileId))
                .leftJoin(postImage).on(postView.postId.eq(postImage.post.postId))
                .leftJoin(postLike).on(postView.postId.eq(postLike.post.postId))
                .leftJoin(userBlock).on(userInfo.userInfoId.eq(userBlock.blockedUserInfo.userInfoId));
    }

    private JPAQuery<PostViewDTO.PostViewResponse> getPostViewWithMyCommentResponseJPAQueryUntilJoin(ConstructorExpression<PostViewDTO.PostViewResponse> postViewResponseConstructorExpression) {
        return jpaQueryFactory
                .select(
                        postViewResponseConstructorExpression
                )
                .from(postView)
                .leftJoin(category).on(postView.categoryId.eq(category.categoryId))
                .leftJoin(userInfo).on(postView.userId.eq(userInfo.userId))
                .leftJoin(userProfile).on(userInfo.userProfile.profileId.eq(userProfile.profileId))
                .leftJoin(postImage).on(postView.postId.eq(postImage.post.postId))
                .leftJoin(postLike).on(postView.postId.eq(postLike.post.postId))
                .leftJoin(userBlock).on(userInfo.userInfoId.eq(userBlock.blockedUserInfo.userInfoId))
                .leftJoin(comment).on(comment.post.postId.eq(postView.postId));
    }

    private ConstructorExpression<PostViewDTO.PostViewResponse> getPostViewResponseConstructorExpression(WithUserInfo.AbstractWithUserInfo postViewRequest) {
        return Projections.constructor(PostViewDTO.PostViewResponse.class,
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
                userInfo.userNickname.max().substring(0,1).append("******"),
                postView.likeCount.max(),
                postView.commentCount.max()

        );
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
        return postView.postBodySearch.contains(searchBody.toLowerCase());
    }
    private BooleanExpression isMyPost(Boolean isMyPost,Long myUserInfoId) {
        if (isMyPost == null || isMyPost.equals(false)) {
            return null;
        }
        return userInfo.userInfoId.eq(myUserInfoId);
    }

    private BooleanExpression isMyComment(Long myUserInfoId) {

        return comment.userInfo.userInfoId.eq(myUserInfoId);
    }
}
