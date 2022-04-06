package com.example.restapimvc.post.command.dto;

import lombok.*;

public class EditPostDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EditPostRequest {
        private Long postId;
        private String postBody;
    }
}
