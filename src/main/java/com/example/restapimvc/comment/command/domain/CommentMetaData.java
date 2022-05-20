package com.example.restapimvc.comment.command.domain;

import com.example.restapimvc.comment.command.dto.CommentDto;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.sql.Timestamp;
import java.util.List;

@Embeddable
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentMetaData {

    @Column(insertable = false)
    private Timestamp postTime;

    private Integer likeCount;
    private String status;
    private Integer orderInGroup;
    private Integer groupNum;

    @Column(insertable = false)
    private Boolean isDelete;
    @Column(insertable = false)
    private Boolean isEdit;



    public static CommentMetaData of(CommentDto.WriteCommentRequest writeCommentRequest, List<Comment> comments) {
        return CommentMetaData.builder()
                .status(writeCommentRequest.getStatus())
                .likeCount(0)
                .groupNum(calculateGroupNum(writeCommentRequest,comments))
                .orderInGroup(calculateOrderInGroup(writeCommentRequest,comments))
                .build();
    }

    public static CommentMetaData deletedInstance(CommentMetaData copyTarget) {
        return CommentMetaData.builder()
                .postTime(copyTarget.getPostTime())
                .likeCount(copyTarget.getLikeCount())
                .status(copyTarget.getStatus())
                .groupNum(copyTarget.getGroupNum())
                .orderInGroup(copyTarget.getOrderInGroup())
                .isDelete(Boolean.TRUE)
                .isEdit(copyTarget.getIsEdit())
                .build();
    }

    private static Integer calculateOrderInGroup(CommentDto.WriteCommentRequest writeCommentRequest, List<Comment> comments) {
        if (writeCommentRequest.getStatus().equals("parent")) {
            return 0;
        } else {
            return calculateNextOrderInGroup(comments,writeCommentRequest.getGroupNum());
        }
    }

    private static Integer calculateNextOrderInGroup(List<Comment> comments, Integer groupNum) {
        if (comments.isEmpty()) {
            return 0;
        }
        Integer maxOrderInGroup = 0;
        for (Comment comment: comments) {

            if (maxOrderInGroup < comment.getCommentMetaData().getOrderInGroup() && groupNum.equals(comment.getCommentMetaData().getGroupNum())) {
                maxOrderInGroup = comment.getCommentMetaData().getOrderInGroup();
            }
        }
        return maxOrderInGroup+1;
    }

    private static Integer calculateGroupNum(CommentDto.WriteCommentRequest writeCommentRequest, List<Comment> comments) {
        if (writeCommentRequest.getStatus().equals("child")) {
            return writeCommentRequest.getGroupNum();
        } else {
            return calculateNextGroupNum(comments);
        }
    }

    private static Integer calculateNextGroupNum(List<Comment> comments) {
        if (comments.isEmpty()) {
            return 0;
        }
        Integer maxGroupNum = 0;
        for (Comment comment: comments) {
            if (maxGroupNum < comment.getCommentMetaData().getGroupNum()) {
                maxGroupNum = comment.getCommentMetaData().getGroupNum();
            }
        }
        return maxGroupNum+1;
    }


}
