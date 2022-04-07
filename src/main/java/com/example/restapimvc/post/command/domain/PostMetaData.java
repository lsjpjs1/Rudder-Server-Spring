package com.example.restapimvc.post.command.domain;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import java.sql.Timestamp;

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
}
