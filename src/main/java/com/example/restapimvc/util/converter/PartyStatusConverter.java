package com.example.restapimvc.util.converter;

import com.example.restapimvc.enums.PartyStatus;
import com.example.restapimvc.util.EnumEntityConverter;

import javax.persistence.Converter;

@Converter
public class PartyStatusConverter extends EnumEntityConverter {

    public PartyStatusConverter() {
        super(PartyStatus.class);
    }
}
