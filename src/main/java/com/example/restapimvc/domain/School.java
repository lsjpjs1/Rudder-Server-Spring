package com.example.restapimvc.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity(name = "university")
@Table
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long schoolId;

    private String schoolName;

    private String regex;

    @Override
    public String toString() {
        return "School{" +
                "schoolId=" + schoolId +
                ", schoolName='" + schoolName + '\'' +
                ", regex='" + regex + '\'' +
                '}';
    }

    @Builder
    public School(long schoolId, String schoolName, String regex){
        this.schoolId = schoolId;
        this.schoolName = schoolName;
        this.regex = regex;
    }
}
