package com.example.restapimvc.post.command.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class PostMetaDataDto {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LikePostResponse {
        private Long postId;
        private Integer likeCount;
    }
}
