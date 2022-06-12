package com.example.restapimvc.pre.party.command.dto;

import com.example.restapimvc.common.WithUserInfo;
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
}
