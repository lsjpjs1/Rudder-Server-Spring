package com.example.restapimvc.pre.party.command.domain;

import com.example.restapimvc.domain.CommentLike;
import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.enums.PartyPhase;
import com.example.restapimvc.enums.PartyStatus;
import com.example.restapimvc.exception.CustomException;
import com.example.restapimvc.exception.ErrorCode;
import com.example.restapimvc.pre.party.command.dto.PartyDto;
import com.example.restapimvc.util.converter.PartyPhaseConverter;
import com.example.restapimvc.util.converter.PartyStatusConverter;
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


    @ManyToOne(targetEntity = UserInfo.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "party_host_user_info_id")
    private UserInfo partyHostUserInfo;

    private Long partyChatRoomId;
    @Setter
    private String partyThumbnailName;
    private Long alcoholId;

    @Column(insertable = false)
    @Convert(converter = PartyPhaseConverter.class)
    private PartyPhase partyPhase;

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
                .partyHostUserInfo(createPartyRequest.getHostUserInfo())
                .partyChatRoomId(createPartyRequest.getChatRoomId())
                .alcoholId(1l)
                .build();
    }


    public void apply(UserInfo userInfo, Integer numberApplicants) {
            partyMembers.put(userInfo.getUserInfoId(),
                    PartyMember.builder()
                            .party(this)
                            .partyStatus(PartyStatus.PENDING)
                            .userInfo(userInfo)
                            .numberApplicants(numberApplicants)
                            .build()
            );
    }

    public void cancel() {
        partyPhase = PartyPhase.CANCEL;
    }

    public void stopRecruit() {
        partyPhase = PartyPhase.STOP_RECRUIT;
    }
    public void fixMembers() {
        partyPhase = PartyPhase.FIX_MEMBERS;
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
                        .numberApplicants(1)
                        .build()
        );
    }

    public void throwIfCanceled(){
        if (!partyPhase.equals(PartyPhase.RECRUITING)){
            throw new CustomException(ErrorCode.PARTY_CANCELED);
        }
    }



}
