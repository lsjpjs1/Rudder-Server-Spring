package com.example.restapimvc.enums;

import com.example.restapimvc.payment.PaymentService;
import com.example.restapimvc.util.EnumEntityConvertable;

public enum PaymentStatus  implements EnumEntityConvertable {
    COMPLETE("COMPLETE");

    private String paymentStatus;

    PaymentStatus(String paymentStatus){this.paymentStatus = paymentStatus;}
    @Override
    public String getEntityValue() {
        return paymentStatus;
    }

    @Override
    public Boolean isTargetEnum(String s) {
        return paymentStatus.equals(s);
    }
}
