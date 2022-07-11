package com.example.restapimvc.enums;

import com.example.restapimvc.util.EnumEntityConvertable;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AlcoholUnit implements EnumEntityConvertable {
    BOTTLE("BOTTLE"),
    PACK("PACK");

    private String alcoholUnit;

    AlcoholUnit(String alcoholUnit) {this.alcoholUnit = alcoholUnit;}

    @Override
    public String getEntityValue() {
        return alcoholUnit;
    }

    @Override
    public Boolean isTargetEnum(String s) {
        return alcoholUnit.equals(s);
    }

    @JsonValue
    public String toValue() {
        return alcoholUnit;
    }
}
