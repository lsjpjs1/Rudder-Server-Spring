package com.example.restapimvc.post.command.dto;

import lombok.*;

import java.sql.Timestamp;

public class CommonPostDto {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Builder
    public static class CommonPostResponse {
        private Long postId;
        private String userId;
        private String postBody;
        private Long categoryId;
        private Long schoolId;
        private Timestamp postTime;
    }
}
