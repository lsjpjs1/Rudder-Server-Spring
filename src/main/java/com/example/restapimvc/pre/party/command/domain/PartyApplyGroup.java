package com.example.restapimvc.pre.party.command.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table
@Builder
@AllArgsConstructor
public class PartyApplyGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long partyApplyGroupId;

    private Long hostUserInfoId;
    private Integer targetMemberCount;

}
