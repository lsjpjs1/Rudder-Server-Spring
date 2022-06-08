package com.example.restapimvc.pre.party.command.domain;

import com.example.restapimvc.domain.CommentLike;
import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.enums.PartyStatus;
import com.example.restapimvc.pre.party.command.dto.PartyDto;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "party")
@Table
@Builder
@AllArgsConstructor
public class Party {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long partyId;

    @OneToMany(mappedBy = "party", cascade = CascadeType.ALL)
    @MapKeyColumn(name = "user_info_id")
    private Map<Long, PartyMember> partyMembers;

    public static Party from(PartyDto.CreatePartyRequest createPartyRequest) {
        return Party.builder()
                .build();
    }

    public void apply(List<UserInfo> userInfos) {
        for (UserInfo userInfo : userInfos) {
            partyMembers.put(userInfo.getUserInfoId(),
                    PartyMember.builder()
                            .party(this)
                            .partyStatus(PartyStatus.PENDING)
                            .userInfo(userInfo)
                            .build()
                    );
        }
    }

    public void registerHost(UserInfo userInfo) {
        partyMembers.put(userInfo.getUserInfoId(),
                PartyMember.builder()
                        .party(this)
                        .userInfo(userInfo)
                        .partyStatus(PartyStatus.HOST)
                        .build()
        );
    }



}
