package com.btc.redg.example.postgres.util;

import com.btc.redg.runtime.AttributeMetaInfo;
import com.btc.redg.runtime.transformer.DefaultPreparedStatementParameterSetter;
import com.btc.redg.runtime.transformer.PreparedStatementParameterSetter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class MyPreparedStatementParameterSetter implements PreparedStatementParameterSetter {

    private final DefaultPreparedStatementParameterSetter defaultSetter = new DefaultPreparedStatementParameterSetter();

    @Override
    public void setParameter(final PreparedStatement preparedStatement, final int i, final Object o, final AttributeMetaInfo attributeMetaInfo, final Connection connection) throws SQLException {
        // this one sets the parameter for all ZonedDateTimes and passes the rest to a default setter
        if (ZonedDateTime.class.equals(o.getClass())) {
            final ZonedDateTime zdt = (ZonedDateTime) o;
            preparedStatement.setTimestamp(i, new Timestamp(zdt.withZoneSameInstant(ZoneOffset.UTC).toEpochSecond() * 1000));
        } else {
            defaultSetter.setParameter(preparedStatement, i, o, attributeMetaInfo, connection);
        }

    }
}
