package com.example.restapimvc.enums;

import com.example.restapimvc.util.EnumEntityConvertable;

public enum PartyPhase implements EnumEntityConvertable {
    RECRUITING("RECRUITING"),
    CANCEL("CANCEL"),
    STOP_RECRUIT("STOP_RECRUIT"),
    FIX_MEMBERS("FIX_MEMBERS");

    private String partyPhase;

    PartyPhase(String partyPhase) {
        this.partyPhase = partyPhase;
    }


    @Override
    public String getEntityValue() {
        return partyPhase;
    }

    @Override
    public Boolean isTargetEnum(String s) {
        return partyPhase.equals(s);
    }
}
