package com.example.restapimvc.post.query.dto;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.serializer.ParseImageUrlsSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

public class PostViewDTO {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class PostViewResponseWrapper {
        private List<PostViewResponse> posts;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class PostViewResponse {
        private Long postId;
        private Long userInfoId;
        private String postBody;
        private Timestamp postTime;
        private Long categoryId;
        private String categoryName;
        private String categoryAbbreviation;
        @JsonSerialize(using = ParseImageUrlsSerializer.class)
        private String imageUrls;
        private Boolean isLike;
        private Boolean isMine;
        private String userProfileImageUrl;
        private String userNickname;
        private Integer likeCount;
        private Integer commentCount;

        private static void fillEmptyStringInNullImageUrls(PostViewResponse postViewResponse) {
            if(postViewResponse==null) {
                return;
            }

            if(postViewResponse.imageUrls == null) {
                postViewResponse.imageUrls = "";
            }
        }
        public static void setImageUrlsNonNull(PostViewResponse postViewResponse) {
            fillEmptyStringInNullImageUrls(postViewResponse);
        }

        public static void setImageUrlsNonNull(List<PostViewResponse> postViewResponses) {
            for(PostViewResponse postViewResponse: postViewResponses) {
                fillEmptyStringInNullImageUrls(postViewResponse);
            }
        }

    }

    @Getter
    @Setter
    public static abstract class PostViewRequest {
        private Long userInfoId;
        private String userId;
        private Long schoolId;

        public void setAllUserInfo(UserInfo userInfo) {
            userInfoId = userInfo.getUserInfoId();
            userId = userInfo.getUserId();
            schoolId = userInfo.getSchool().getSchoolId();
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class PostViewMultipleLookUpRequest extends PostViewRequest{
        private Long categoryId;
        private String searchBody;
        private Long endPostId;
        private Long writerUserInfoId;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class PostViewSingleLookUpRequest extends PostViewRequest{
        private Long postId;
    }


}