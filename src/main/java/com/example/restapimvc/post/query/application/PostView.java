package com.example.restapimvc.post.query.application;

import lombok.Getter;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Timestamp;

@Entity
@Table(name = "board")
@Getter
public class PostView {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    private String userId;
    private String postBody;
    private Timestamp postTime;
    private Long categoryId;
    private Long schoolId;
    private Boolean isDelete;
    private Boolean isEdit;
    private Integer commentCount;
    private Integer likeCount;
    private Boolean isImageUploading;
    private String postBodySearch;


}
