package com.example.restapimvc.comment.command.domain;

import com.example.restapimvc.comment.command.dto.CommentDto;
import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.domain.UserProfile;
import com.example.restapimvc.post.command.domain.Post;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "board_comment")
@Table
@Builder
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ManyToOne(targetEntity = Post.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(targetEntity = UserInfo.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_user_info_id")
    private UserInfo userInfo;

    @Setter
    private String commentBody;

    private String userId;

    @Embedded
    private CommentMetaData commentMetaData;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return commentId.equals(comment.commentId) && commentBody.equals(comment.commentBody) && commentMetaData.equals(comment.commentMetaData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, commentBody, commentMetaData);
    }

    public static Comment of(CommentDto.WriteCommentRequest writeCommentRequest, List<Comment> comments, UserInfo userInfo, Post post) {
        return Comment.builder()
                .post(post)
                .userInfo(userInfo)
                .commentBody(writeCommentRequest.getCommentBody())
                .userId(writeCommentRequest.getUserId())
                .commentMetaData(
                        CommentMetaData.of(writeCommentRequest, comments)
                )
                .build();
    }

    public void editCommentBody(String commentBody) {
        this.commentBody = commentBody;

    }
}
