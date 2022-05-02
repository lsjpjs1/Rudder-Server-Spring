package com.example.restapimvc.job.command.domain;

import com.example.restapimvc.post.command.domain.PostImage;
import com.example.restapimvc.post.command.domain.PostLike;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "job")
@Table
@Builder
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Job {
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

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
    @MapKeyColumn(name = "userInfoId")
    private Map<Long, JobFavorite> jobFavorites;

    public void favorite(JobFavorite jobFavorite) {
        jobFavorites.put(jobFavorite.getUserInfoId(),jobFavorite);
    }
}
