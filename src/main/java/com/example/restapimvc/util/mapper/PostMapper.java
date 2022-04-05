package com.example.restapimvc.util.mapper;

import com.example.restapimvc.post.command.domain.Post;
import com.example.restapimvc.post.command.dto.WritePostDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PostMapper {
    @Mapping(target="postTime", source="postMetaData.postTime")
    WritePostDto.WritePostResponse entityToWritePostResponse(Post post);
}
