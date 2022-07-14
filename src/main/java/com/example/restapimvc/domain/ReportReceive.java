package com.example.restapimvc.domain;

import com.example.restapimvc.enums.ReportType;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table
@AllArgsConstructor
@Builder
public class ReportReceive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;

    private ReportType reportType;

    private String reportBody;

    private Long userInfoId;

    private Long itemId;
}
