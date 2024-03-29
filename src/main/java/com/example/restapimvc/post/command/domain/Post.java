package com.example.restapimvc.post.command.domain;


import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.post.command.dto.EditPostDto;
import com.example.restapimvc.util.RandomNumber;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

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

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Set<PostImage> postImages;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @MapKeyColumn(name = "userId")
    private Map<String, PostLike> postLikes;

    private String postBodySearch;

    public void addComment() {
        postMetaData = postMetaData.copyWithOverwrite(
                PostMetaData.builder()
                        .commentCount(postMetaData.getCommentCount()+1)
                        .build()
        );
    }

    public void view() {
        postMetaData.increaseViewCount();
    }

    public void setImageUploading() {
        postMetaData.setIsImageUploadingTrue();
    }

    public void edit(EditPostDto.EditPostRequest editPostRequest) {
        this.postBody = editPostRequest.getPostBody();
        postMetaData.setEditFlagTrue();
    }

    public void completeImageUpload() {
        postMetaData.finishImageUpload();
    }

    public void appendPostImages(List<String> fileNames) {
        if (postImages == null) {
            postImages = new LinkedHashSet<>();
        }
        if (postImages.isEmpty()) {
            postImages = new LinkedHashSet<>();
        }
        postImages.addAll(
                fileNames.stream()
                .map(s -> PostImage.builder()
                        .fileName(s)
                        .post(this)
                        .build())
                .collect(Collectors.toList())
        );



    }

    public void delete() {
        postMetaData.setDeleteFlagTrue();
    }

    public void like(UserInfo userInfo) {
        if (postLikes == null) {
            postLikes = new HashMap<>();
        }
        PostLike postLike = postLikes.get(userInfo.getUserId());
        if (postLike == null) {
            postLike = PostLike.builder().post(this).userId(userInfo.getUserId()).isCanceled(false).build();
            postLikes.put(userInfo.getUserId(), postLike);
        } else {
            postLike.changeCancelState();
        }
        postMetaData.calculateLikeCount(postLike.getIsCanceled());


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
