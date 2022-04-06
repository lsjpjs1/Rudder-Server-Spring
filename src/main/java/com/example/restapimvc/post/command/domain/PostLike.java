package com.example.restapimvc.post.command.domain;


import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity(name = "board_like")
@Table
@AllArgsConstructor
@Builder
@DynamicInsert
@DynamicUpdate
public class PostLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_like_id")
    private Long postLikeId;

    @ManyToOne(targetEntity = Post.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    private String userId;

    private Boolean isCanceled;

    public Boolean changeCancelState() {
        this.isCanceled = !this.isCanceled;
        return this.isCanceled;
    }
}
