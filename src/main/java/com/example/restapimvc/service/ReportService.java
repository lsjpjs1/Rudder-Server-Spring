package com.example.restapimvc.service;

import com.example.restapimvc.domain.ReportReceive;
import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.dto.ReportDto;
import com.example.restapimvc.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;

    @Transactional
    public void report(UserInfo userInfo, ReportDto.ReportRequest reportRequest) {
        reportRequest.setAllUserInfo(userInfo);
        ReportReceive reportReceive = ReportReceive.builder()
                .reportType(reportRequest.getReportType())
                .userInfoId(reportRequest.getUserInfoId())
                .reportBody(reportRequest.getReportBody())
                .itemId(reportRequest.getItemId())
                .build();
        reportRepository.save(reportReceive);
    }
}
