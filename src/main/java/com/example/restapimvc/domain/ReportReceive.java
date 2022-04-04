package com.example.restapimvc.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table
@AllArgsConstructor
public class ReportReceive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;


    private String reportBody;

    private String userId;

    private Long itemId;
}
