package com.example.restapimvc.util.mapper;

import com.example.restapimvc.comment.command.domain.Comment;
import com.example.restapimvc.comment.command.dto.CommentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CommentMapper {
    @Mapping(target="postTime", source="commentMetaData.postTime")
    @Mapping(target="status", source="commentMetaData.status")
    @Mapping(target="groupNum", source="commentMetaData.groupNum")
    @Mapping(target="orderInGroup", source="commentMetaData.orderInGroup")
    CommentDto.CommonCommentResponse entityToWriteCommentResponse(Comment comment);
}
