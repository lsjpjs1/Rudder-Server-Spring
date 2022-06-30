package com.example.restapimvc.enums;

import com.example.restapimvc.dto.NoticeDTO;
import com.example.restapimvc.util.EnumEntityConvertable;
import lombok.Getter;

@Getter
public enum PartyStatus implements EnumEntityConvertable {
    HOST("HOST"),
    PENDING("PENDING"),
    ALCOHOL_PENDING("ALCOHOL_PENDING"),
    APPROVE("APPROVE"),
    REJECT("REJECT");

    private String status;

    PartyStatus(String status) {
        this.status = status;
    }


    @Override
    public String getEntityValue() {
        return status;
    }

    @Override
    public Boolean isTargetEnum(String s) {
        return status.equals(s);
    }



}
