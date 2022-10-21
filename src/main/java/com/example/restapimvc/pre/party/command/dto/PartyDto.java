package com.example.restapimvc.pre.party.command.dto;

import com.example.restapimvc.common.FileMetaData;
import com.example.restapimvc.common.WithUserInfo;
import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.enums.FilteringPeriod;
import com.example.restapimvc.enums.PartyStatus;
import com.example.restapimvc.pre.party.command.domain.Alcohol;
import com.example.restapimvc.pre.party.command.domain.PickUpPlace;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

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
        @ApiModelProperty(hidden = true)
        private UserInfo hostUserInfo;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    @Builder
    public static class RatePartyRequest extends WithUserInfo.AbstractWithUserInfo {
        private Double partyRate;

        @ApiModelProperty(hidden = true)
        private Long partyId;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    @Builder
    public static class ApplyPartyRequest extends WithUserInfo.AbstractWithUserInfo {
        @ApiModelProperty(hidden = true)
        private Long partyId;
        private Integer numberApplicants;
        private String recommendationCode = "";
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
        private Integer numberApplicants;
        private String userNickname;
        private Long partyMemberId;
        private Boolean isChatExist;
        @ApiModelProperty(dataType = "com.example.restapimvc.enums.PartyStatus")
        private String partyStatus;

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
    public static class PartyPreviewDto {
        private Long partyId;
        private String partyTitle;
        private String partyThumbnailUrl;
        private Timestamp partyTime;
        private Integer totalNumberOfMember;
        private Integer currentNumberOfMember;
        private Integer applyCount;
        private String universityName;
        private String universityLogoUrl;
        @ApiModelProperty(dataType = "com.example.restapimvc.enums.PartyStatus")
        private String partyStatus;
        private Long partyChatRoomId;
        private Boolean isChatExist;
        @ApiModelProperty(dataType = "com.example.restapimvc.enums.PartyPhase")
        private String partyPhase;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    @Builder
    public static class GetPartiesRequest extends WithUserInfo.AbstractWithUserInfo {
        private Long endPartyId;
        private FilteringPeriod filteringPeriod;
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
    public static class PartyMemberDto {
        private Long userInfoId;
        private Long partyProfileId;
        private String userNickname;
        private String partyProfileImageUrl;
        private PartyStatus partyStatus;
    }



    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    @Builder
    public static class GetPartyDetailResponse {
        private PartyDetailDto partyDetail;
        private List<PartyMemberDto> partyMembers;
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
        private Integer totalNumberOfMember;
        private Integer currentNumberOfMember;
        private Integer applyCount;
        private String universityName;
        private String partyLocation;
        private String partyDescription;
        @ApiModelProperty(dataType = "com.example.restapimvc.enums.PartyStatus")
        private String partyStatus;
        @ApiModelProperty(dataType = "com.example.restapimvc.enums.PartyPhase")
        private String partyPhase;
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



    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class PartyThumbnailUploadUrlRequest extends WithUserInfo.AbstractWithUserInfo {
        private FileMetaData imageMetaData;
        private Long partyId;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    @Builder
    public static class GetPickUpPlaceResponse {
        private List<PickUpPlace> pickUpPlaces;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    @Builder
    public static class GetAlcoholResponse {
        private List<Alcohol> alcoholList;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    @Builder
    public static class CreatePartyResponse {
        private Long partyId;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    @Builder
    public static class SendCustomerSoundRequest extends WithUserInfo.AbstractWithUserInfo {
        private Long partyId;
        private String customerSoundType;
        private String customerSoundBody;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    @Builder
    public static class ApproveApplyRequest extends WithUserInfo.AbstractWithUserInfo {
        private Long partyMemberId;
        @ApiModelProperty(hidden = true)
        private Long partyId;
    }

}
