package com.example.restapimvc.pre.party.command.domain;

import com.example.restapimvc.domain.CommentLike;
import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.enums.PartyStatus;
import com.example.restapimvc.pre.party.command.dto.PartyDto;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashMap;
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
    private String partyTitle;
    private String location;
    private String partyDescription;
    private Timestamp partyTime;
    private Integer totalNumberOfMember;
    private Integer currentNumberOfMember;
    private Long partyHostUserInfoId;
    private Long partyChatRoomId;

    @OneToMany(mappedBy = "party", cascade = CascadeType.ALL)
    @MapKeyColumn(name = "user_info_id")
    private Map<Long, PartyMember> partyMembers;

    public static Party from(PartyDto.CreatePartyRequest createPartyRequest) {
        return Party.builder()
                .partyTitle(createPartyRequest.getPartyTitle())
                .location(createPartyRequest.getLocation())
                .partyDescription(createPartyRequest.getPartyDescription())
                .partyTime(createPartyRequest.getPartyTime())
                .totalNumberOfMember(createPartyRequest.getTotalNumberOfMember())
                .currentNumberOfMember(1)
                .partyHostUserInfoId(createPartyRequest.getUserInfoId())
                .partyChatRoomId(createPartyRequest.getChatRoomId())
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

    public void apply(UserInfo userInfo) {
            partyMembers.put(userInfo.getUserInfoId(),
                    PartyMember.builder()
                            .party(this)
                            .partyStatus(PartyStatus.PENDING)
                            .userInfo(userInfo)
                            .build()
            );
    }

    public void registerHost(UserInfo userInfo) {
        if(partyMembers==null){
            partyMembers = new HashMap<>();
        }
        partyMembers.put(userInfo.getUserInfoId(),
                PartyMember.builder()
                        .party(this)
                        .userInfo(userInfo)
                        .partyStatus(PartyStatus.HOST)
                        .build()
        );
    }



}
