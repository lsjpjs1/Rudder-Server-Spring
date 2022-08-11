package com.example.restapimvc.pre.party.command.domain;

import com.example.restapimvc.domain.UserInfo;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table
@Builder
@AllArgsConstructor
public class PartyRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long partyRatingId;

    @ManyToOne(targetEntity = UserInfo.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_info_id")
    private UserInfo userInfo;

    @ManyToOne(targetEntity = Party.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "party_id")
    private Party party;

    private Double partyRate;
}
