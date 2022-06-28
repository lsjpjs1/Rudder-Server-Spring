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
public class UserPartyProfileImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_party_profile_image_id")
    private Long partyProfileImageId;

    @Column(name = "user_party_profile_id")
    private Long partyProfileId;

    @Column(name = "user_party_profile_image_name")
    private Long partyProfileImageName;
}
