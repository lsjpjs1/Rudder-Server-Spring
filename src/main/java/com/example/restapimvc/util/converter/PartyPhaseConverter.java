package com.example.restapimvc.util.converter;

import com.example.restapimvc.enums.AlcoholUnit;
import com.example.restapimvc.enums.PartyPhase;
import com.example.restapimvc.util.EnumEntityConverter;

public class PartyPhaseConverter extends EnumEntityConverter {
    public PartyPhaseConverter() {
        super(PartyPhase.class);
    }
}
