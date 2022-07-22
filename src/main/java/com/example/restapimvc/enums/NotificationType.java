package com.example.restapimvc.enums;

import com.example.restapimvc.util.EnumEntityConvertable;

public enum NotificationType implements EnumEntityConvertable {
    PARTY_APPLY("PARTY_APPLY");

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
