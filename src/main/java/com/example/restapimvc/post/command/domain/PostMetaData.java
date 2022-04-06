package com.example.restapimvc.post.command.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.sql.Timestamp;

@Embeddable
@Getter
@ToString
public class PostMetaData {
    private Timestamp postTime;

    private Integer commentCount;

    private Integer likeCount;

    @Column(name = "post_view")
    private Integer viewCount;

    private Boolean isDelete;

    private Boolean isEdit;

    public void increaseViewCount() {
        this.viewCount +=1;
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
