package com.example.restapimvc.util.converter;

import com.example.restapimvc.enums.PaymentStatus;
import com.example.restapimvc.util.EnumEntityConverter;

public class PaymentStatusConverter extends EnumEntityConverter {
    public PaymentStatusConverter() {super(PaymentStatus.class);}
}
