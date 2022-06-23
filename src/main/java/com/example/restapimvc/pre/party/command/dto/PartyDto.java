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
        private String partyBody;
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
        private String distanceFromUser;
        private Integer totalNumberOfMember;
        private Integer currentNumberOfMember;
        private Integer applyCount;
        private String universityName;
    }


}
