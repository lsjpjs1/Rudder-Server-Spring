package com.example.restapimvc.post.command.dto;

import lombok.*;

import java.util.List;

public class FileDto {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class UploadUrl {
        private String url;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class UploadUrlsWrapper {
        private List<UploadUrl> uploadUrls;
    }


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class UrlsWrapper {
        private List<String> urls;
    }
}
