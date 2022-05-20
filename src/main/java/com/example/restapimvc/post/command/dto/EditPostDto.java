package com.example.restapimvc.post.command.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

public class EditPostDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EditPostRequest {
        @ApiModelProperty(hidden = true)
        private Long postId;
        private String postBody;
    }
}
