package com.example.restapimvc.util.converter;

import com.example.restapimvc.enums.AlcoholUnit;
import com.example.restapimvc.util.EnumEntityConverter;

public class AlcoholUnitConverter extends EnumEntityConverter {
    public AlcoholUnitConverter() {
        super(AlcoholUnit.class);
    }
}
