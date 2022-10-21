package com.example.restapimvc.domain;

import com.example.restapimvc.enums.ReportType;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table
@AllArgsConstructor
@Builder
@DynamicInsert
@DynamicUpdate
@ToString
public class RecommendationCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codeId;

    private String code;

    private Long partyMemberId;

}
