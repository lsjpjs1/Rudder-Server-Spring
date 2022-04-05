package com.example.restapimvc.post.command.dto;

import lombok.*;

import java.sql.Timestamp;

public class WritePostDto {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class WritePostRequest {
        private String postBody;
        private Long categoryId;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Builder
    public static class WritePostResponse {
        private Long postId;
        private String userId;
        private String postBody;
        private Long categoryId;
        private Long schoolId;
        private Timestamp postTime;
    }
}
