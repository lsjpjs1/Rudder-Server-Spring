package com.example.restapimvc.pre.party.controller;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.dto.SchoolDTO;
import com.example.restapimvc.enums.PartyStatus;
import com.example.restapimvc.exception.ErrorResponse;
import com.example.restapimvc.pre.party.command.application.GetPartyService;
import com.example.restapimvc.pre.party.command.application.PartyDashBoardService;
import com.example.restapimvc.pre.party.command.dto.PartyDto;
import com.example.restapimvc.security.CustomSecurityContextHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags = "파티 대시보드 관련")
public class PartyDashBoardController {

    private PartyDashBoardService partyDashBoardService;
    private GetPartyService getPartyService;

    @Operation(summary = "파티 지원자 목록")
    @GetMapping(value = "/parties/{partyId}/applicants")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "성공")
    })
    public ResponseEntity<PartyDto.GetPartyApplicantsResponse> getPartyApplicants(@PathVariable Long partyId) {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        PartyDto.GetPartyApplicantsRequest getPartyApplicantsRequest = PartyDto.GetPartyApplicantsRequest.builder().partyId(partyId).build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(PartyDto.GetPartyApplicantsResponse.builder().applicants(
                        new ArrayList<PartyDto.PartyApplicantsDto>(
                                Arrays.asList(PartyDto.PartyApplicantsDto.builder().userInfoId(335l).partyProfileImageUrl("http://image.kyobobook.co.kr/images/book/xlarge/224/x9788954626224.jpg").build(),
                                        PartyDto.PartyApplicantsDto.builder().userInfoId(335l).partyProfileImageUrl("https://t1.daumcdn.net/cfile/tistory/25433C4B56EFA6B42F").build())
                        )
                ).build());
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(partyDashBoardService.getPartyApplicants(userInfoFromToken,getPartyApplicantsRequest));

    }

    @Operation(summary = "내가 개설한 파티 날짜 목록")
    @GetMapping(value = "/parties/my-host")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "성공")
    })
    public ResponseEntity<PartyDto.GetPartiesMyHostResponse> getPartiesMyHost() {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        PartyDto.GetPartiesMyHostResponse.builder().parties(
                                new ArrayList(
                                        Arrays.asList(
                                                PartyDto.PartyOnlyDateDto.builder().partyId(1l).partyDate(new Timestamp(System.currentTimeMillis())).build(),
                                                PartyDto.PartyOnlyDateDto.builder().partyId(2l).partyDate(new Timestamp(System.currentTimeMillis())).build()
                                        )
                                )
                        ).build()
                );
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(getPartyService.getPartiesMyHost(userInfoFromToken));
    }

    @Operation(summary = "수락된 파티 목록")
    @GetMapping(value = "/parties/approved")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "성공")
    })
    public ResponseEntity<PartyDto.GetPartiesResponse> getApprovedParties() {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        PartyDto.GetPartiesResponse.builder()
                                .parties(
                                        new ArrayList(
                                                Arrays.asList(
                                                        PartyDto.PartyPreviewDto.builder().partyId(1l).partyTime(new Timestamp(System.currentTimeMillis())).partyTitle("완전 수락된 파티").partyStatus(PartyStatus.APPROVE.getStatus()).applyCount(8).currentNumberOfMember(3).totalNumberOfMember(10).distanceFromUser("100km").universityName("koreauniv").partyThumbnailUrl("http://t1.daumcdn.net/friends/prod/editor/dc8b3d02-a15a-4afa-a88b-989cf2a50476.jpg").build(),
                                                        PartyDto.PartyPreviewDto.builder().partyId(2l).partyTime(new Timestamp(System.currentTimeMillis())).partyTitle("사용자의 술 수락 대기 중인 파티").partyStatus(PartyStatus.ALCOHOL_PENDING.getStatus()).applyCount(8).currentNumberOfMember(3).totalNumberOfMember(10).distanceFromUser("300km").universityName("yonsei").partyThumbnailUrl("http://t1.daumcdn.net/friends/prod/editor/dc8b3d02-a15a-4afa-a88b-989cf2a50476.jpg").build()
                                                )
                                        )
                                )
                                .build()
                );
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(getPartyService.getApprovedParties(userInfoFromToken));
    }


    @Operation(summary = "신청하고 대기 중인 파티 목록")
    @GetMapping(value = "/parties/pending")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "성공")
    })
    public ResponseEntity<PartyDto.GetPartiesResponse> getPendingParties() {
        UserInfo userInfoFromToken = CustomSecurityContextHolder.getUserInfoFromToken();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        PartyDto.GetPartiesResponse.builder()
                                .parties(
                                        new ArrayList(
                                                Arrays.asList(
                                                        PartyDto.PartyPreviewDto.builder().partyId(1l).partyTime(new Timestamp(System.currentTimeMillis())).partyTitle("수락 대기 중인 파티1").partyStatus(PartyStatus.PENDING.getStatus()).applyCount(8).currentNumberOfMember(3).totalNumberOfMember(10).distanceFromUser("100km").universityName("koreauniv").partyThumbnailUrl("http://t1.daumcdn.net/friends/prod/editor/dc8b3d02-a15a-4afa-a88b-989cf2a50476.jpg").build(),
                                                        PartyDto.PartyPreviewDto.builder().partyId(2l).partyTime(new Timestamp(System.currentTimeMillis())).partyTitle("수락 대기 중인 파티2").partyStatus(PartyStatus.PENDING.getStatus()).applyCount(8).currentNumberOfMember(3).totalNumberOfMember(10).distanceFromUser("300km").universityName("yonsei").partyThumbnailUrl("http://t1.daumcdn.net/friends/prod/editor/dc8b3d02-a15a-4afa-a88b-989cf2a50476.jpg").build()
                                                )
                                        )
                                )
                                .build()
                );
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(getPartyService.getPendingParties(userInfoFromToken));
    }

}