package com.example.restapimvc.comment.command.dto;

import com.example.restapimvc.common.WithUserInfo;
import lombok.*;

import java.util.List;

public class CommentDto {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class GetCommentsRequest extends WithUserInfo.AbstractWithUserInfo {
        private List<String> categoryTypes;
        private Boolean isUserSelectCategory;
    }

//    @Getter
//    @Setter
//    @AllArgsConstructor
//    @NoArgsConstructor
//    @Builder
//    public static class GetCommentsResponse {
//    }
//
//    @Getter
//    @Setter
//    @AllArgsConstructor
//    @NoArgsConstructor
//    @Builder
//    public static class CommentResponse {
//
//    }
}
