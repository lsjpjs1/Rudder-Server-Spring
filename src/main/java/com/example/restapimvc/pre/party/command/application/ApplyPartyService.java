package com.example.restapimvc.pre.party.command.application;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.exception.CustomException;
import com.example.restapimvc.exception.ErrorCode;
import com.example.restapimvc.pre.party.command.domain.Party;
import com.example.restapimvc.pre.party.command.domain.PartyRepository;
import com.example.restapimvc.pre.party.command.dto.PartyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApplyPartyService {

    private final PartyRepository partyRepository;

    @Transactional
    public void applyParty(UserInfo userInfo, PartyDto.ApplyPartyRequest applyPartyRequest) {
        applyPartyRequest.setAllUserInfo(userInfo);
        Party party = partyRepository.findById(applyPartyRequest.getPartyId())
                .orElseThrow(()->new CustomException(ErrorCode.PARTY_NOT_FOUND));

    }

}
