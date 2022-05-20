package com.example.restapimvc.post.command.domain;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.Objects;

@Embeddable
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostMetaData {
    private Timestamp postTime;

    private Integer commentCount;

    private Integer likeCount;

    @Column(name = "post_view")
    private Integer viewCount;

    private Boolean isDelete;

    private Boolean isEdit;

    private Boolean isImageUploading;


    public PostMetaData copyWithOverwrite(PostMetaData newPostMetaData) {
        return PostMetaData.builder()
                .postTime(newPostMetaData.getPostTime() == null? postTime : newPostMetaData.getPostTime())
                .commentCount(newPostMetaData.getCommentCount() == null? commentCount : newPostMetaData.getCommentCount())
                .likeCount(newPostMetaData.getLikeCount() == null? likeCount : newPostMetaData.getLikeCount())
                .viewCount(newPostMetaData.getViewCount() == null? viewCount : newPostMetaData.getViewCount())
                .isDelete(newPostMetaData.getIsDelete() == null? isDelete : newPostMetaData.getIsDelete())
                .isEdit(newPostMetaData.getIsEdit() == null? isEdit : newPostMetaData.getIsEdit())
                .isImageUploading(newPostMetaData.getIsImageUploading() == null? isImageUploading : newPostMetaData.getIsImageUploading())
                .build();
    }

    public void increaseViewCount() {
        this.viewCount +=1;
    }

    public void setIsImageUploadingTrue() {
        this.isImageUploading = true;
    }

    public void setEditFlagTrue() {
        if(!this.isEdit) {
            this.isEdit = true;
        }
    }
    public void setDeleteFlagTrue() {
        if(!this.isDelete) {
            this.isDelete = true;
        }
    }
    private void increaseLikeCount() {
        this.likeCount+=1;
    }
    private void decreaseLikeCount() {
        this.likeCount-=1;
    }
    public void calculateLikeCount(Boolean isCanceled) {
        if (isCanceled) {
            decreaseLikeCount();
        } else{
            increaseLikeCount();
        }
    }

    public void finishImageUpload() {
        this.isImageUploading=false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostMetaData that = (PostMetaData) o;
        return Objects.equals(postTime, that.postTime) && Objects.equals(commentCount, that.commentCount) && Objects.equals(likeCount, that.likeCount) && Objects.equals(viewCount, that.viewCount) && Objects.equals(isDelete, that.isDelete) && Objects.equals(isEdit, that.isEdit) && Objects.equals(isImageUploading, that.isImageUploading);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postTime, commentCount, likeCount, viewCount, isDelete, isEdit, isImageUploading);
    }
}
