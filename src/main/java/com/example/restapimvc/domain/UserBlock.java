package com.example.restapimvc.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table
public class UserBlock{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userBlockId;

    @ManyToOne(targetEntity = UserInfo.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_info_id")
    private UserInfo userInfo;

    @ManyToOne(targetEntity = UserInfo.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "blocked_user_info_id")
    private UserInfo blockedUserInfo;

    @Override
    public String toString() {
        return "UserBlock{" +
                "userBlockId=" + userBlockId +
                '}';
    }

    @Builder
    public UserBlock(UserInfo userInfo, UserInfo blockedUserInfo){
        this.userInfo = userInfo;
        this.blockedUserInfo = blockedUserInfo;
    }
}
