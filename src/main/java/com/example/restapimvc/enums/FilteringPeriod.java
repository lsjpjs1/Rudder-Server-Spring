package com.example.restapimvc.enums;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

public enum FilteringPeriod implements ConvertibleTimestampPeriod{
    TOMORROW{
        @Override
        public Timestamp getStart() {
            return getNow();
        }

        @Override
        public Timestamp getEnd() {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE,2);
            setMidnight(calendar);
            return new Timestamp(calendar.getTimeInMillis());
        }
    },
    THIS_WEEKENDS {
        @Override
        public Timestamp getStart() {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
            setMidnight(calendar);
            return new Timestamp(calendar.getTimeInMillis());
        }

        @Override
        public Timestamp getEnd() {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
            calendar.add(Calendar.DATE, 2);
            setMidnight(calendar);
            return new Timestamp(calendar.getTimeInMillis());
        }
    },
    THIS_WEEK {
        @Override
        public Timestamp getStart() {
            return getNow();
        }

        @Override
        public Timestamp getEnd() {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE,8);
            setMidnight(calendar);
            return new Timestamp(calendar.getTimeInMillis());
        }
    },
    THIS_MONTH {
        @Override
        public Timestamp getStart() {
            return getNow();
        }

        @Override
        public Timestamp getEnd() {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE,31);
            setMidnight(calendar);
            return new Timestamp(calendar.getTimeInMillis());
        }
    };




}
