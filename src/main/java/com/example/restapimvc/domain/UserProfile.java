package com.example.restapimvc.domain;

import com.example.restapimvc.dto.UserProfileDto;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table
@AllArgsConstructor
@DynamicInsert
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;

    //간단한 자기소개 내용
    @Setter
    private String profileBody;

    @Setter
    @Column(name = "user_profile_image_id")
    private Long profileImageId;

    @Override
    public String toString() {
        return "UserProfile{" +
                "profileId=" + profileId +
                ", profileBody='" + profileBody + '\'' +
                ", profileImageId=" + profileImageId +
                '}';
    }

    @Builder
    public UserProfile(String profileBody, Long profileImageId) {
        this.profileBody = profileBody;
        this.profileImageId = profileImageId;
    }



}
