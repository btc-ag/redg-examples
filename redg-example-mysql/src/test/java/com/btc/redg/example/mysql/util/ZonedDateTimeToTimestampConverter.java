package com.btc.redg.example.mysql.util;

import java.sql.Timestamp;
import java.time.ZonedDateTime;

public class ZonedDateTimeToTimestampConverter {

    public static <T> T convertDate(ZonedDateTime time, Class<T> type) {
        if (!Timestamp.class.equals(type)) {
            throw new UnsupportedOperationException("Can only convert to java.sql.Timestamp");
        }
        return type.cast(new Timestamp(time.toEpochSecond() * 1000));
    }
}
