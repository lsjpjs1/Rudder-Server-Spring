package com.example.restapimvc.util.converter;

import com.example.restapimvc.enums.AlcoholUnit;
import com.example.restapimvc.enums.ReportType;
import com.example.restapimvc.util.EnumEntityConverter;

public class ReportTypeConverter extends EnumEntityConverter {
    public ReportTypeConverter() {
        super(ReportType.class);
    }
}

