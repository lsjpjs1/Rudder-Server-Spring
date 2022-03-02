package com.example.restapimvc.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table
public class UserRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestId;

    @ManyToOne(targetEntity = UserInfo.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_info_id")
    private UserInfo userInfo;

    private String body;

    @Builder
    public UserRequest(UserInfo userInfo, String body) {
        this.userInfo = userInfo;
        this.body = body;
    }

    @Override
    public String toString() {
        return "UserRequest{" +
                "requestId=" + requestId +
                ", userInfo=" + userInfo +
                ", body='" + body + '\'' +
                '}';
    }


}
