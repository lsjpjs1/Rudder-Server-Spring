package com.example.restapimvc.comment.command.domain;

import com.example.restapimvc.comment.command.dto.CommentDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;
    private final QComment comment = QComment.comment;

//    public List<CommentDto.CommentResponse> findComments(CommentDto.GetCommentsRequest getCommentsRequest) {
//        return jpaQueryFactory
//                .select(
//                        Projections.constructor(CommentDto.CommentResponse.class,
//                                comment.commentId,
//                                comment.commentBody,
//                                comment.userInfo.userInfoId,
//                                comment.commentMetaData.postTime,
//                                comment.commentMetaData.likeCount,
//                                comment.commentMetaData.status,
//                                comment.commentMetaData.orderInGroup,
//                                comment.commentMetaData.groupNum,
//                                comment.commentMetaData.isDelete,
//                                //isMine,
//                                //isLiked,
//                                //userProfileImageUrl
//                                )
//                )
//                .from(comment)
//                .leftJ
//    }

}
