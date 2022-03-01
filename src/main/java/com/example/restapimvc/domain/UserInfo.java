package com.example.restapimvc.domain;

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
    private long userInfoId;

    private String userId;

    private int schoolId;

    private String userPassword;

    @Override
    public String toString() {
        return "UserInfo{" +
                "userInfoId=" + userInfoId +
                ", userId='" + userId + '\'' +
                ", schoolId=" + schoolId +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }

    @Builder
    public UserInfo(long userInfoId, String userId, int schoolId, String userPassword){
        this.userInfoId = userInfoId;
        this.userId = userId;
        this.schoolId = schoolId;
        this.userPassword = userPassword;
    }

    public void clearUserPassword() {
        this.userPassword = null;
    }
}
