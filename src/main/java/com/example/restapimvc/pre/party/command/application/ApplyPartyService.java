package com.example.restapimvc.pre.party.command.application;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.exception.CustomException;
import com.example.restapimvc.exception.ErrorCode;
import com.example.restapimvc.pre.party.command.domain.Party;
import com.example.restapimvc.pre.party.command.domain.PartyRepository;
import com.example.restapimvc.pre.party.command.dto.PartyDto;
import com.example.restapimvc.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplyPartyService {

    private final PartyRepository partyRepository;
    private final UserInfoRepository userInfoRepository;

    @Transactional
    public void applyParty(UserInfo userInfo, PartyDto.ApplyPartyRequest applyPartyRequest) {
        applyPartyRequest.setAllUserInfo(userInfo);
        Party party = partyRepository.findById(applyPartyRequest.getPartyId())
                .orElseThrow(()->new CustomException(ErrorCode.PARTY_NOT_FOUND));
        List<UserInfo> userInfos = userInfoRepository.findByUserInfoIdIn(applyPartyRequest.getUserInfoIdList());
        party.apply(userInfos);
        partyRepository.save(party);
    }

}
