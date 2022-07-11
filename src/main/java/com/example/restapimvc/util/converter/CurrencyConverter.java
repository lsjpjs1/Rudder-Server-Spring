package com.example.restapimvc.util.converter;

import com.example.restapimvc.enums.Currency;
import com.example.restapimvc.util.EnumEntityConverter;

public class CurrencyConverter  extends EnumEntityConverter {
    public CurrencyConverter() {super(Currency.class);}
}
