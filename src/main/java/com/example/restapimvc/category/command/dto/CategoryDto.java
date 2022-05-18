package com.example.restapimvc.category.command.dto;

import com.example.restapimvc.common.WithUserInfo;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.*;

import java.util.List;

public class CategoryDto {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class GetCategoriesRequest extends WithUserInfo.AbstractWithUserInfo {
        private List<String> categoryTypes;
        private Boolean isUserSelectCategory;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class JoinClubRequest extends WithUserInfo.AbstractWithUserInfo {
        private Long categoryId;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class UserSelectCategoryRequest extends WithUserInfo.AbstractWithUserInfo {
        private List<Long> categories;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class GetCategoriesResponse {
        private List<CategoryResponse> categories;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CategoryResponse {
        private Long categoryId;
        private String categoryType;
        private String categoryName;
        private String categoryAbbreviation;
        private Boolean isSelect;
        private String isMember;
    }
}
