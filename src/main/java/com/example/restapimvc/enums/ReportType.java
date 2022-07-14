package com.example.restapimvc.enums;

import com.example.restapimvc.util.EnumEntityConvertable;

public enum ReportType implements EnumEntityConvertable {
    USER("USER");

    private String reportType;

    ReportType(String reportType) {this.reportType = reportType;}
    @Override
    public String getEntityValue() {
        return reportType;
    }

    @Override
    public Boolean isTargetEnum(String s) {
        return reportType.equals(s);
    }


}
