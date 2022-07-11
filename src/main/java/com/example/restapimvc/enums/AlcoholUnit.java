package com.example.restapimvc.enums;

import com.example.restapimvc.util.EnumEntityConvertable;

public enum AlcoholUnit implements EnumEntityConvertable {
    BOTTLE("BOTTLE");

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
}
