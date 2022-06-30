package com.example.restapimvc.pre.party.command.domain;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.enums.PartyStatus;
import com.example.restapimvc.post.command.domain.Post;
import com.example.restapimvc.util.converter.PartyStatusConverter;
import com.example.restapimvc.util.converter.UserInfoOsConverter;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "party_member")
@Table
@Builder
@AllArgsConstructor
public class PartyMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long partyMemberId;

    @ManyToOne(targetEntity = UserInfo.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_info_id")
    private UserInfo userInfo;

    @ManyToOne(targetEntity = Party.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "party_id")
    private Party party;

    @Convert(converter = PartyStatusConverter.class)
    private PartyStatus partyStatus;

    public void approve() {
        partyStatus = PartyStatus.APPROVE;
    }

    public void reject() {
        partyStatus = PartyStatus.REJECT;
    }
}
