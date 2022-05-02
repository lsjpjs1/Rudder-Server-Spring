package com.example.restapimvc.job.query.dto;

import com.example.restapimvc.common.WithUserInfo;
import com.example.restapimvc.post.query.dto.PostViewDTO;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

public class JobDaoDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class JobDaoRequest extends WithUserInfo.AbstractWithUserInfo {
        private Long endJobId;
        private String searchBody;
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
    public static class JobDaoResponse {
        private Long jobId;
        private String jobTitle;
        private String jobType;
        private String companyName;
        private String salary;
        private Timestamp uploadDate;
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

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class JobDaoResponseWrapper {
        private List<JobDaoResponse> jobResponses;
    }
}
