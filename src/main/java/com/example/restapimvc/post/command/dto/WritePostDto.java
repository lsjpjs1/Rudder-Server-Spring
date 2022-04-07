package com.example.restapimvc.post.command.dto;

import com.example.restapimvc.common.FileMetaData;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class ImageUploadUrlRequest {


        private List<FileMetaData> imageMetaData;


    }




}
