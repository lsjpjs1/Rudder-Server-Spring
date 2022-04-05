package com.example.restapimvc.post.command.domain;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "board")
@Table
@Builder
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private String userId;

    private String postBody;

    private Long categoryId;

    private Long schoolId;

    @Embedded
    private PostMetaData postMetaData;

    @OneToMany(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "post_id")
    private List<PostImage> postImages;

    public void viewPost() {
        postMetaData.increaseViewCount();
    }

    public void edit(String editPostBody) {
        this.postBody = editPostBody;
        postMetaData.setEditFlagTrue();
    }

    public void delete() {
        postMetaData.setDeleteFlagTrue();
    }


    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", userId='" + userId + '\'' +
                ", postBody='" + postBody + '\'' +
                ", categoryId=" + categoryId +
                ", schoolId=" + schoolId +
                ", postMetaData=" + postMetaData +
                '}';
    }
}
