package com.example.restapimvc.util.converter;

import com.example.restapimvc.enums.Currency;
import com.example.restapimvc.enums.NotificationType;
import com.example.restapimvc.util.EnumEntityConverter;

public class NotificationTypeConverter  extends EnumEntityConverter {
    public NotificationTypeConverter() {super(NotificationType.class);}
}