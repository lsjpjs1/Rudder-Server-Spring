package com.example.restapimvc.pre.party.command.dto;

import com.example.restapimvc.common.WithUserInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public class PartyDto {
    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    @Builder
    public static class CreatePartyRequest extends WithUserInfo.AbstractWithUserInfo {
        private String partyTitle;
        private String location;
        private String partyDescription;
        private Timestamp partyTime;
        private Integer totalNumberOfMember;
        @ApiModelProperty(hidden = true)
        private Long chatRoomId;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    @Builder
    public static class ApplyPartyRequest extends WithUserInfo.AbstractWithUserInfo {
        @ApiModelProperty(hidden = true)
        private Long partyId;
        private List<Long> userInfoIdList;
    }
    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    @Builder
    public static class GetApplyListRequest extends WithUserInfo.AbstractWithUserInfo {

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    @Builder
    public static class GetPartyApplicantsRequest extends WithUserInfo.AbstractWithUserInfo {
        @ApiModelProperty(hidden = true)
        private Long partyId;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    @Builder
    public static class PartyApplicantsDto {
        private Long userInfoId;
        private String partyProfileImageUrl;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    @Builder
    public static class GetPartyApplicantsResponse {
        private List<PartyApplicantsDto> applicants;
    }


    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    @Builder
    public static class PartyDetailDto {
        private Long partyId;
        private String partyTitle;
        private String partyThumbnailUrl;
        private Timestamp partyTime;
        private String distanceFromUser;
        private Integer totalNumberOfMember;
        private Integer currentNumberOfMember;
        private Integer applyCount;
        private String universityName;
        private String partyLocation;
        private List<String> alcoholList;
        private String partyDescription;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    @Builder
    public static class PartyPreviewDto {
        private Long partyId;
        private String partyTitle;
        private String partyThumbnailUrl;
        private Timestamp partyTime;
        private Integer totalNumberOfMember;
        private Integer currentNumberOfMember;
        private Integer applyCount;
        private String universityName;
        private String partyStatus;
    }


    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    @Builder
    public static class GetPartiesRequest extends WithUserInfo.AbstractWithUserInfo {

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    @Builder
    public static class GetPartiesResponse {
        private List<PartyPreviewDto> parties;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    @Builder
    public static class GetPartyDetailRequest extends WithUserInfo.AbstractWithUserInfo {
        @ApiModelProperty(hidden = true)
        private Long partyId;
    }


    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    @Builder
    public static class GetPartyDetailResponse {
        private Long partyId;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    @Builder
    public static class PartyOnlyDateDto {
        private Long partyId;
        private Timestamp partyDate;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    @Builder
    public static class GetPartiesMyHostResponse {
        private List<PartyOnlyDateDto> parties;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    @Builder
    public static class CreatePartyApplyGroupRequest extends WithUserInfo.AbstractWithUserInfo {
        @ApiModelProperty(hidden = true)
        private Long partyId;
        private Integer targetMemberCount;
    }


}
