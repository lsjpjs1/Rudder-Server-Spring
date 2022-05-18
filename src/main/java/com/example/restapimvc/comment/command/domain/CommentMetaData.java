package com.example.restapimvc.comment.command.domain;

import lombok.*;

import javax.persistence.Embeddable;
import java.sql.Timestamp;

@Embeddable
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentMetaData {

    private Timestamp postTime;
    private Integer likeCount;
    private String status;
    private Integer orderInGroup;
    private Integer groupNum;
    private Boolean isDelete;
    private Boolean isEdit;

}
