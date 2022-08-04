package com.example.restapimvc.pre.party.command.application;

import com.example.restapimvc.domain.CustomerRequest;
import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.exception.CustomException;
import com.example.restapimvc.exception.ErrorCode;
import com.example.restapimvc.payment.PaymentHistory;
import com.example.restapimvc.pre.party.command.domain.Party;
import com.example.restapimvc.pre.party.command.domain.PartyMember;
import com.example.restapimvc.pre.party.command.domain.PartyMemberRepository;
import com.example.restapimvc.pre.party.command.domain.PartyRepository;
import com.example.restapimvc.pre.party.command.dto.PartyDto;
import com.example.restapimvc.repository.CustomerRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PartyConfigService {

    private final PartyRepository partyRepository;
    private final CustomerRequestRepository customerRequestRepository;
    private final PartyMemberRepository partyMemberRepository;

    @Transactional
    public void cancelParty(UserInfo userInfo, Long partyId) {
        Party party = partyRepository.findById(partyId)
                .orElseThrow(() -> new CustomException(ErrorCode.PARTY_NOT_FOUND));
        if(!party.getPartyHostUserInfo().getUserInfoId().equals(userInfo.getUserInfoId())){
            throw new CustomException(ErrorCode.NO_PERMISSION);
        }
        party.cancel();
        partyRepository.save(party);
    }


    @Transactional
    public void stopRecruit(UserInfo userInfo, Long partyId) {
        Party party = partyRepository.findById(partyId)
                .orElseThrow(() -> new CustomException(ErrorCode.PARTY_NOT_FOUND));
        if(!party.getPartyHostUserInfo().getUserInfoId().equals(userInfo.getUserInfoId())){
            throw new CustomException(ErrorCode.NO_PERMISSION);
        }
        party.stopRecruit();
        partyRepository.save(party);
    }

    @Transactional
    public void fixMembers(UserInfo userInfo, Long partyId) {
        Party party = partyRepository.findById(partyId)
                .orElseThrow(() -> new CustomException(ErrorCode.PARTY_NOT_FOUND));
        if(!party.getPartyHostUserInfo().getUserInfoId().equals(userInfo.getUserInfoId())){
            throw new CustomException(ErrorCode.NO_PERMISSION);
        }
        party.fixMembers();
        partyRepository.save(party);
    }

    @Transactional
    public void sendCustomerSound(UserInfo userInfo, PartyDto.SendCustomerSoundRequest sendCustomerSoundRequest) {
        sendCustomerSoundRequest.setAllUserInfo(userInfo);
        CustomerRequest customerRequest = CustomerRequest.builder().customerRequestType(sendCustomerSoundRequest.getCustomerSoundType())
                .customerRequestBody(sendCustomerSoundRequest.getCustomerSoundBody())
                .userInfoId(userInfo.getUserInfoId())
                .build();
        customerRequestRepository.save(customerRequest);



    }
}
