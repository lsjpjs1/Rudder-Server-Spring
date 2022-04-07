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
        private Boolean isImageExist;
    }

}
