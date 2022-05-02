package com.example.restapimvc.job.command.domain;

import com.example.restapimvc.post.command.domain.Post;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity(name = "job_favorite")
@Table
@AllArgsConstructor
@Builder
@DynamicInsert
@DynamicUpdate
public class JobFavorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobFavoriteId;
    @ManyToOne(targetEntity = Job.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    private Job job;
    private Long userInfoId;
}
