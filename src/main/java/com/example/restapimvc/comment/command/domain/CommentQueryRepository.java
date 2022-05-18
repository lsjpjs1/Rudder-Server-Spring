package com.example.restapimvc.comment.command.domain;

import com.example.restapimvc.comment.command.dto.CommentDto;
import com.example.restapimvc.domain.QCommentLike;
import com.example.restapimvc.domain.QUserBlock;
import com.example.restapimvc.domain.QUserProfile;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentQueryRepository {
    @Value("${cloud-front.url.profile-image-preview}")
    private String CLOUD_FRONT_PROFILE_IMAGE_PREVIEW_URL;

    private final JPAQueryFactory jpaQueryFactory;
    private final QComment comment = QComment.comment;
    private final QCommentLike commentLike = QCommentLike.commentLike;
    private final QUserBlock userBlock = QUserBlock.userBlock;
    private final QUserProfile userProfile = QUserProfile.userProfile;

    public List<CommentDto.CommentResponse> findComments(CommentDto.GetCommentsRequest getCommentsRequest) {
        return jpaQueryFactory
                .select(
                        Projections.constructor(CommentDto.CommentResponse.class,
                                comment.commentId,
                                comment.commentBody.max(),
                                comment.userInfo.userInfoId.max(),
                                comment.commentMetaData.postTime.max(),
                                comment.commentMetaData.likeCount.max(),
                                comment.commentMetaData.status.max(),
                                comment.commentMetaData.orderInGroup,
                                comment.commentMetaData.groupNum,
                                new CaseBuilder()
                                        .when(comment.commentMetaData.isDelete.eq(true)).then(1)
                                        .otherwise(0)
                                        .max()
                                        .eq(1),
                                new CaseBuilder()
                                        .when(comment.userInfo.userInfoId.eq(getCommentsRequest.getUserInfoId())).then(1)
                                        .otherwise(0)
                                        .max()
                                        .eq(1),
                                new CaseBuilder()
                                        .when(commentLike.userId.eq(getCommentsRequest.getUserId())).then(1)
                                        .otherwise(0)
                                        .max()
                                        .eq(1),
                                userProfile.profileImageId.max().stringValue().prepend(CLOUD_FRONT_PROFILE_IMAGE_PREVIEW_URL),
                                comment.userInfo.userNickname.max()
                        )
                )
                .from(comment)
                .leftJoin(commentLike).on(commentLike.commentId.eq(comment.commentId))
                .leftJoin(userBlock).on(userBlock.blockedUserInfo.userInfoId.eq(comment.userInfo.userInfoId))
                .leftJoin(userProfile).on(userProfile.profileId.eq(comment.userInfo.userProfile.profileId))
                .where(
                        comment.post.postId.eq(getCommentsRequest.getPostId()),
                        comment.commentMetaData.isDelete.isFalse()
                )
                .groupBy(comment.commentId,comment.commentMetaData.groupNum,comment.commentMetaData.orderInGroup)
                .having(
                        new CaseBuilder()
                                .when(userBlock.userInfo.userInfoId.eq(getCommentsRequest.getUserInfoId())).then(1)
                                .otherwise(0)
                                .sum()
                                .eq(0)
                )
                .orderBy(comment.commentMetaData.groupNum.asc(),comment.commentMetaData.orderInGroup.asc())
                .fetch();
    }

}
