package com.example.restapimvc.job.query.application;

import com.example.restapimvc.scraper.common.FindTarget;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "job")
@Getter
@DynamicInsert
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobId;
    private String companyName;
    private String jobTitle;
    private Timestamp uploadDate;
    private String url;
    private String jobType;
    private String expireDate;
    private String location;
    private String salary;
    private String searchSummary;
    private String jobDescription;

    public static JobDao from(FindTarget findTarget) {
        return JobDao.builder()
                .companyName(findTarget.getCompanyName())
                .jobTitle(findTarget.getJobTitle())
                .url(findTarget.getUrl())
                .jobType(findTarget.getJobType())
                .expireDate(findTarget.getExpireIn())
                .location(findTarget.getLocation())
                .salary(findTarget.getSalary())
                .searchSummary(findTarget.getSearchSummary())
                .build();
    }


}
