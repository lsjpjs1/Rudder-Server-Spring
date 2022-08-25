package com.example.restapimvc.enums;

import com.example.restapimvc.util.EnumEntityConvertable;

public enum NotificationType implements EnumEntityConvertable {
    PARTY_APPLY("PARTY_APPLY"),
    PARTY_ACCEPTED("PARTY_ACCEPTED"),
    PARTY_RECRUIT_END_IN_24H("PARTY_RECRUIT_END_IN_24H"),
    PARTY_RATING("PARTY_RATING"),
    PARTY_ALCOHOL_PAY_REQUEST("PARTY_ALCOHOL_PAY_REQUEST");

    private String notificationType;

    NotificationType(String notificationType){this.notificationType = notificationType;}

    @Override
    public String getEntityValue() {
        return notificationType;
    }

    @Override
    public Boolean isTargetEnum(String s) {
        return notificationType.equals(s);
    }

}
