package com.example.restapimvc.comment.command.dto;

import com.example.restapimvc.common.WithUserInfo;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

public class CommentDto {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class GetCommentsRequest extends WithUserInfo.AbstractWithUserInfo {
        private Long postId;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class GetCommentsResponse {
        private List<CommentResponse> comments;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CommentResponse {
        private Long commentId;
        private String commentBody;
        private Long userInfoId;
        private Timestamp postTime;
        private Integer likeCount;
        private String status;
        private Integer orderInGroup;
        private Integer groupNum;
        private Boolean isDelete;
        private Boolean isMine;
        private Boolean isLiked;
        private String userProfileImageUrl;
        private String userNickname;
    }
}
