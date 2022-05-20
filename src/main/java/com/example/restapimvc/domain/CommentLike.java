package com.example.restapimvc.domain;

import com.example.restapimvc.comment.command.domain.Comment;
import com.example.restapimvc.post.command.domain.Post;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity(name = "board_comment_like")
@Table
@AllArgsConstructor
@Builder
public class CommentLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_comment_like_id")
    private Long commentLikeId;

    private String userId;

    @ManyToOne(targetEntity = Comment.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    private Boolean isCanceled;

    public Boolean changeCancelState() {
        this.isCanceled = !this.isCanceled;
        return this.isCanceled;
    }

}
