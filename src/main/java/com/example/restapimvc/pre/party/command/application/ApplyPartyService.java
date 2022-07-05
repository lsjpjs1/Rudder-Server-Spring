package com.example.restapimvc.pre.party.command.application;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.exception.CustomException;
import com.example.restapimvc.exception.ErrorCode;
import com.example.restapimvc.pre.party.command.domain.Party;
import com.example.restapimvc.pre.party.command.domain.PartyApplyGroup;
import com.example.restapimvc.pre.party.command.domain.PartyApplyGroupMember;
import com.example.restapimvc.pre.party.command.domain.PartyRepository;
import com.example.restapimvc.pre.party.command.dto.PartyDto;
import com.example.restapimvc.repository.PartyApplyGroupMemberRepository;
import com.example.restapimvc.repository.PartyApplyGroupRepository;
import com.example.restapimvc.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplyPartyService {

    private final PartyRepository partyRepository;
    private final PartyApplyGroupRepository partyApplyGroupRepository;
    private final PartyApplyGroupMemberRepository partyApplyGroupMemberRepository;
    private final UserInfoRepository userInfoRepository;

    @PersistenceContext
    private final EntityManager entityManager;

    @Transactional
    public void applyParty(UserInfo userInfo, PartyDto.ApplyPartyRequest applyPartyRequest) {
        applyPartyRequest.setAllUserInfo(userInfo);
        Party party = partyRepository.findById(applyPartyRequest.getPartyId())
                .orElseThrow(()->new CustomException(ErrorCode.PARTY_NOT_FOUND));
        List<UserInfo> userInfos = userInfoRepository.findByUserInfoIdIn(applyPartyRequest.getUserInfoIdList());
        party.apply(userInfos);
        partyRepository.save(party);
    }

    @Transactional
    public void createPartyApplyGroup(UserInfo userInfo, PartyDto.CreatePartyApplyGroupRequest createPartyApplyGroupRequest) {
        createPartyApplyGroupRequest.setAllUserInfo(userInfo);
        PartyApplyGroup partyApplyGroup = PartyApplyGroup.builder()
                .partyId(createPartyApplyGroupRequest.getPartyId())
                .hostUserInfoId(createPartyApplyGroupRequest.getUserInfoId())
                .targetMemberCount(createPartyApplyGroupRequest.getTargetMemberCount())
                .build();
        partyApplyGroupRepository.save(partyApplyGroup);
        PartyApplyGroupMember partyApplyGroupMember = PartyApplyGroupMember.builder()
                .partyApplyGroup(partyApplyGroup)
                .userInfoId(createPartyApplyGroupRequest.getUserInfoId())
                .build();
        partyApplyGroupMemberRepository.save(partyApplyGroupMember);
    }

    @Transactional
    public void joinPartyApplyGroup(UserInfo userInfo, Long applyGroupId) {
        PartyApplyGroup partyApplyGroup = partyApplyGroupRepository.findById(applyGroupId)
                .orElseThrow(() -> new CustomException(ErrorCode.PARTY_APPLY_GROUP_NOT_FOUND));
        PartyApplyGroupMember partyApplyGroupMember = PartyApplyGroupMember.builder()
                .partyApplyGroup(partyApplyGroup)
                .userInfoId(userInfo.getUserInfoId())
                .build();
        partyApplyGroupMemberRepository.save(partyApplyGroupMember);
        entityManager.refresh(partyApplyGroupMember);
        List<PartyApplyGroupMember> partyApplyGroupMembers = partyApplyGroupMemberRepository.findByByPartyApplyGroup(partyApplyGroup);
        if(partyApplyGroupMembers.size()==partyApplyGroup.getTargetMemberCount()) {
            Party party = partyRepository.findById(partyApplyGroup.getPartyId())
                    .orElseThrow(()->new CustomException(ErrorCode.PARTY_NOT_FOUND));
            partyApplyGroupMembers.stream()
                    .forEach(member->{
                        party.apply(userInfoRepository.findById(member.getUserInfoId()).orElseThrow(()->new CustomException(ErrorCode.USER_INFO_NOT_FOUND)));
                    });
            partyRepository.save(party);
        }
    }

}
