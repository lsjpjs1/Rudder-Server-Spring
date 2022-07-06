package com.example.restapimvc.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table
@AllArgsConstructor
@Builder
@Setter
public class UserPartyProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_party_profile_id")
    private Long partyProfileId;

    @Column(name = "user_party_profile_body")
    private String partyProfileBody;

    @Column(name = "user_party_profile_image_id")
    private Long userPartyProfileImageId;
}
