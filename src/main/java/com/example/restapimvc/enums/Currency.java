package com.example.restapimvc.enums;

import com.example.restapimvc.util.EnumEntityConvertable;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Currency implements EnumEntityConvertable {
    POUND("£");

    private String currencySymbol;

    Currency(String currencySymbol) {this.currencySymbol = currencySymbol;}

    @Override
    public String getEntityValue() {
        return currencySymbol;
    }

    @Override
    public Boolean isTargetEnum(String s) {
        return currencySymbol.equals(s);
    }

    @JsonValue
    public String toValue() {
        return currencySymbol;
    }
}
