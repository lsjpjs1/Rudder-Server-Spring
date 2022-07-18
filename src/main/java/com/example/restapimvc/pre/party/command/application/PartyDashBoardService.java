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
    public void approveApply(UserInfo userInfo, PartyDto.ApproveApplyRequest approveApplyRequest) {
        PartyMember partyMember = partyMemberRepository.findById(approveApplyRequest.getPartyMemberId())
                .orElseThrow(() -> new CustomException(ErrorCode.PARTY_MEMBER_NOT_FOUND));
        try {
            if (!partyMember.getParty().getPartyMembers().get(userInfo.getUserInfoId()).getPartyStatus().equals(PartyStatus.HOST)) {
                throw new CustomException(ErrorCode.NO_PERMISSION);
            }
        } catch (Exception e) {
            throw new CustomException(ErrorCode.NO_PERMISSION);
        }


        partyMember.approve();

        partyMemberRepository.save(partyMember);

    }

    @Transactional
    public PartyDto.GetPartyApplicantsResponse getPartyApplicants(UserInfo userInfo, PartyDto.GetPartyApplicantsRequest getPartyApplicantsRequest) {
        getPartyApplicantsRequest.setAllUserInfo(userInfo);
        List<PartyDto.PartyApplicantsDto> applicants = partyQueryRepository.findApplicants(getPartyApplicantsRequest);
        return PartyDto.GetPartyApplicantsResponse.builder().applicants(applicants).build();
    }


}
