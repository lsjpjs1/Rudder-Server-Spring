package com.example.restapimvc.pre.party.command.application;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.exception.CustomException;
import com.example.restapimvc.exception.ErrorCode;
import com.example.restapimvc.pre.party.command.domain.Party;
import com.example.restapimvc.pre.party.command.domain.PartyRating;
import com.example.restapimvc.pre.party.command.domain.PartyRatingRepository;
import com.example.restapimvc.pre.party.command.domain.PartyRepository;
import com.example.restapimvc.pre.party.command.dto.PartyDto;
import com.example.restapimvc.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PartyRatingService {

    private final PartyRatingRepository partyRatingRepository;

    private final PartyRepository partyRepository;
    private final UserInfoRepository userInfoRepository;

    @Transactional
    public void rateParty(UserInfo userInfo, PartyDto.RatePartyRequest ratePartyRequest) {
        ratePartyRequest.setAllUserInfo(userInfo);
        Party party = partyRepository.findById(ratePartyRequest.getPartyId())
                .orElseThrow(() -> new CustomException(ErrorCode.PARTY_NOT_FOUND));
        UserInfo userInfoPersistence = userInfoRepository.findById(ratePartyRequest.getUserInfoId()).get();
        PartyRating partyRating = PartyRating.builder()
                .partyRate(ratePartyRequest.getPartyRate())
                .party(party)
                .userInfo(userInfoPersistence)
                .build();
        partyRatingRepository.save(partyRating);
    }

}
