package com.example.restapimvc.domain;

import com.example.restapimvc.dto.UserProfileDto;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;

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
    public UserProfile(Long profileId, String profileBody, Long profileImageId) {
        this.profileId = profileId;
        this.profileBody = profileBody;
        this.profileImageId = profileImageId;
    }

    public UserProfileDto.UserProfileResponse toResponseObject() {
        return UserProfileDto.UserProfileResponse.builder()
                .profileId(profileId)
                .profileBody(profileBody)
                .profileImageId(profileImageId)
                .build();
    }
}
