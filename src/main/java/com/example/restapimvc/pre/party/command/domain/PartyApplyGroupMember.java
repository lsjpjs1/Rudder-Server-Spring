package com.example.restapimvc.pre.party.command.domain;

import com.example.restapimvc.domain.School;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table
@Builder
@AllArgsConstructor
public class PartyApplyGroupMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long partyApplyGroupMemberId;

    @ManyToOne(targetEntity = PartyApplyGroup.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "partyApplyGroupId")
    private PartyApplyGroup partyApplyGroup;

    private Long userInfoId;
}
