package com.example.restapimvc.enums;

import java.sql.Timestamp;
import java.util.Calendar;

public interface ConvertibleTimestampPeriod {

    default Timestamp getNow(){
        return new Timestamp(System.currentTimeMillis());
    }

    default void setMidnight(Calendar calendar){
        calendar.set(Calendar.HOUR,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        calendar.set(Calendar.HOUR_OF_DAY,0);
    }
    Timestamp getStart();
    Timestamp getEnd();
}
