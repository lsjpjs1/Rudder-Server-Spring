package com.example.restapimvc.pre.party.command.application;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.enums.PartyStatus;
import com.example.restapimvc.exception.CustomException;
import com.example.restapimvc.exception.ErrorCode;
import com.example.restapimvc.pre.party.command.domain.*;
import com.example.restapimvc.pre.party.command.dto.PartyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PartyDashBoardService {
    private final PartyMemberRepository partyMemberRepository;
    private final PartyQueryRepository partyQueryRepository;
    private final PartyRepository partyRepository;

    @Transactional
    public void getApplyList(UserInfo userInfo, PartyDto.GetApplyListRequest getApplyListRequest) {
        getApplyListRequest.setAllUserInfo(userInfo);
        //미완
        partyQueryRepository.findApplies(getApplyListRequest);
    }


    @Transactional
    public void approveApply(UserInfo userInfo, Long partyMemberId, Boolean isApprove) {
        PartyMember partyMember = partyMemberRepository.findById(partyMemberId)
                .orElseThrow(() -> new CustomException(ErrorCode.PARTY_MEMBER_NOT_FOUND));
        try{
            if (!partyMember.getParty().getPartyMembers().get(userInfo.getUserInfoId()).getPartyStatus().equals(PartyStatus.HOST)) {
                throw new CustomException(ErrorCode.NO_PERMISSION);
            }
        } catch (Exception e){
            throw new CustomException(ErrorCode.NO_PERMISSION);
        }

        if(isApprove){
            partyMember.approve();
        } else{
            partyMember.reject();
        }
        partyMemberRepository.save(partyMember);

    }

    @Transactional
    public PartyDto.GetPartyApplicantsResponse getPartyApplicants(UserInfo userInfo, PartyDto.GetPartyApplicantsRequest getPartyApplicantsRequest) {
        getPartyApplicantsRequest.setAllUserInfo(userInfo);
        List<PartyDto.PartyApplicantsDto> applicants = partyQueryRepository.findApplicants(getPartyApplicantsRequest);
        return PartyDto.GetPartyApplicantsResponse.builder().applicants(applicants).build();
    }

    @Transactional
    public void cancelParty(UserInfo userInfo, Long partyId) {
        Party party = partyRepository.findById(partyId)
                .orElseThrow(() -> new CustomException(ErrorCode.PARTY_NOT_FOUND));
        if(!party.getPartyHostUserInfoId().equals(userInfo.getUserInfoId())){
            throw new CustomException(ErrorCode.NO_PERMISSION);
        }
        party.cancel();

    }


}
