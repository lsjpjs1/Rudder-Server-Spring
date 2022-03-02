package com.example.restapimvc.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userInfoId;

    private String userId;

    @ManyToOne(targetEntity = School.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    private School school;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String userPassword;

    @Override
    public String toString() {
        return "UserInfo{" +
                "userInfoId=" + userInfoId +
                ", userId='" + userId + '\'' +
                ", school=" + school +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }

    @Builder
    public UserInfo(long userInfoId, String userId, School school, String userPassword){
        this.userInfoId = userInfoId;
        this.userId = userId;
        this.school = school;
        this.userPassword = userPassword;
    }

}
