package com.example.restapimvc.dto;

import com.example.restapimvc.domain.School;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class CategoryDTO {


    @Getter
    @AllArgsConstructor
    public static class CategoriesResponse {
        private long categoryId;
        private String categoryName;
        private String isMember;
        private String categoryType;
    }

    @Getter
    @AllArgsConstructor
    public static class CommonCategoryResponse {
        private long categoryId;
        private String categoryName;
        private Boolean isSelect;
    }

    public enum CategoryMemberType {
        NOT_MEMBER("f"),
        MEMBER("t"),
        PENDING("r"),
        NONE(null);

        private final String name;
        CategoryMemberType(String name) {
            this.name = name;
        }
    }

}
