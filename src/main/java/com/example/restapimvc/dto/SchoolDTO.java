package com.example.restapimvc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public interface SchoolDTO {
    @Getter
    @AllArgsConstructor
    @Builder
    @Setter
    public static class SchoolForResponse {
        private Long schoolId;
        private String schoolName;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    @Setter
    public static class GetSchoolsResponse {
        private List<SchoolForResponse> schools;
    }
}
