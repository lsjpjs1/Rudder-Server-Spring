package com.example.restapimvc.pre.party.command.application;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.enums.NotificationType;
import com.example.restapimvc.enums.PartyStatus;
import com.example.restapimvc.exception.CustomException;
import com.example.restapimvc.exception.ErrorCode;
import com.example.restapimvc.notification.Notification;
import com.example.restapimvc.notification.service.SendNotificationService;
import com.example.restapimvc.pre.party.command.domain.*;
import com.example.restapimvc.pre.party.command.dto.PartyDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PartyDashBoardService {
    private final PartyMemberRepository partyMemberRepository;
    private final PartyQueryRepository partyQueryRepository;
    private final PartyRepository partyRepository;
    private final SendNotificationService sendNotificationService;

    @Transactional
    public void getApplyList(UserInfo userInfo, PartyDto.GetApplyListRequest getApplyListRequest) {
        getApplyListRequest.setAllUserInfo(userInfo);
        //미완
        partyQueryRepository.findApplies(getApplyListRequest);
    }


    @Transactional
    public void approveApply(UserInfo userInfo, PartyDto.ApproveApplyRequest approveApplyRequest) {
        log.info(approveApplyRequest.toString());
        PartyMember partyMember = partyMemberRepository.findById(approveApplyRequest.getPartyMemberId())
                .orElseThrow(() -> new CustomException(ErrorCode.PARTY_MEMBER_NOT_FOUND));
        Party party = partyRepository.findById(approveApplyRequest.getPartyId())
                .orElseThrow(() -> new CustomException(ErrorCode.PARTY_NOT_FOUND));
        try {
            if (!party.getPartyHostUserInfo().getUserInfoId().equals(userInfo.getUserInfoId())) {
                throw new CustomException(ErrorCode.NO_PERMISSION);
            }
        } catch (Exception e) {
            throw new CustomException(ErrorCode.NO_PERMISSION);
        }


        partyMember.approve();

        partyMemberRepository.save(partyMember);

        Notification notification = Notification.builder()
                .notificationType(NotificationType.PARTY_ACCEPTED)
                .itemId(party.getPartyId())
                .notificationTitle("Your application has been accepted")
                .notificationBody("")
                .userInfoId(party.getPartyHostUserInfo().getUserInfoId())
                .build();
        sendNotificationService.sendNotificationAsync(notification);

    }

    @Transactional
    public PartyDto.GetPartyApplicantsResponse getPartyApplicants(UserInfo userInfo, PartyDto.GetPartyApplicantsRequest getPartyApplicantsRequest) {
        getPartyApplicantsRequest.setAllUserInfo(userInfo);
        List<PartyDto.PartyApplicantsDto> applicants = partyQueryRepository.findApplicants(getPartyApplicantsRequest);
        return PartyDto.GetPartyApplicantsResponse.builder().applicants(applicants).build();
    }


}
