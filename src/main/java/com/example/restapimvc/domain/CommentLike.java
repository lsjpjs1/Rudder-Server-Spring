package com.example.restapimvc.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity(name = "board_comment_like")
@Table
public class CommentLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_comment_like_id")
    private Long commentLikeId;

    private String userId;

    private Long commentId;
}
