package com.example.restapimvc.pre.party.command.application;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.exception.CustomException;
import com.example.restapimvc.exception.ErrorCode;
import com.example.restapimvc.pre.party.command.domain.Party;
import com.example.restapimvc.pre.party.command.domain.PartyQueryRepository;
import com.example.restapimvc.pre.party.command.dto.PartyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetPartyService {

    private final PartyQueryRepository partyQueryRepository;

    @Transactional
    public PartyDto.GetPartiesResponse getParties(UserInfo userInfo, PartyDto.GetPartiesRequest getPartiesRequest) {
        getPartiesRequest.setAllUserInfo(userInfo);
        List<PartyDto.PartyPreviewDto> parties = partyQueryRepository.findParties(getPartiesRequest);
        return PartyDto.GetPartiesResponse.builder().parties(parties).build();
    }

    @Transactional
    public PartyDto.GetPartyDetailResponse getPartyDetail(UserInfo userInfo, PartyDto.GetPartyDetailRequest getPartyDetailRequest) {
        getPartyDetailRequest.setAllUserInfo(userInfo);
        PartyDto.GetPartyDetailResponse partyDetail = partyQueryRepository.findPartyDetail(getPartyDetailRequest);
        return partyDetail;
    }


    @Transactional
    public PartyDto.GetPartiesMyHostResponse getPartiesMyHost(UserInfo userInfo) {
        List<PartyDto.PartyOnlyDateDto> partiesMyHost = partyQueryRepository.findPartiesMyHost(userInfo.getUserInfoId());
        return PartyDto.GetPartiesMyHostResponse.builder().parties(partiesMyHost).build();
    }

    @Transactional
    public PartyDto.GetPartiesResponse getApprovedParties(UserInfo userInfo) {
        List<PartyDto.PartyPreviewDto> approvedParties = partyQueryRepository.findApprovedParties(userInfo);
        return PartyDto.GetPartiesResponse.builder().parties(approvedParties).build();
    }

    @Transactional
    public PartyDto.GetPartiesResponse getPendingParties(UserInfo userInfo) {
        List<PartyDto.PartyPreviewDto> approvedParties = partyQueryRepository.findPendingParties(userInfo);
        return PartyDto.GetPartiesResponse.builder().parties(approvedParties).build();
    }

}
