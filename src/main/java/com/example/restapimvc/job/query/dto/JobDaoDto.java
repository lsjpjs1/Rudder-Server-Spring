package com.example.restapimvc.job.query.dto;

import com.example.restapimvc.common.WithUserInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

public class JobDaoDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class JobDaoRequest extends WithUserInfo.AbstractWithUserInfo {
        @ApiModelProperty(
                name = "endJobId"
                , value = "paging 기준이 되는 jobId"
                , required = false
                , dataType = "Long")
        private Long endJobId;
        @ApiModelProperty(
                name = "searchBody"
                , value = "검색 내용"
                , required = false
                , dataType = "String")
        private String searchBody;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class MyFavoriteJobDaoRequest extends WithUserInfo.AbstractWithUserInfo {
        @ApiModelProperty(
                name = "endJobId"
                , value = "paging 기준이 되는 jobId"
                , required = false
                , dataType = "Long")
        private Long endJobId;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class JobDaoDetailRequest extends WithUserInfo.AbstractWithUserInfo {
        private Long jobId;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class JobDaoSummaryResponse {
        private Long jobId;
        private String jobTitle;
        private String jobType;
        private String companyName;
        private String salary;
        private Timestamp uploadDate;
        private Boolean isFavorite;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class JobDaoDetailResponse {
        private Long jobId;
        private String jobTitle;
        private String jobType;
        private String companyName;
        private String salary;
        private Timestamp uploadDate;
        private String location;
        private String jobUrl;
        private String expireDate;
        private Boolean isFavorite;
        private String jobDescription;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class JobDaoResponseWrapper {
        private List<JobDaoSummaryResponse> jobs;
    }
}
