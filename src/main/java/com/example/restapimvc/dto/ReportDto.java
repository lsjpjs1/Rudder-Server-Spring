package com.example.restapimvc.dto;

import com.example.restapimvc.common.WithUserInfo;
import com.example.restapimvc.enums.ReportType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

public class ReportDto {
    @Getter
    @AllArgsConstructor
    @Builder
    @Setter
    public static class ReportRequest extends WithUserInfo.AbstractWithUserInfo {
        private Long itemId;
        private String reportBody;
        private ReportType reportType;
    }
}
