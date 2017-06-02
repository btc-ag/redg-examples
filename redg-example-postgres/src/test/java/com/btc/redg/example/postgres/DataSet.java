package com.btc.redg.example.postgres;

import com.btc.redg.example.postgres.util.MyPreparedStatementParameterSetter;
import com.btc.redg.generated.RedG;
import com.btc.redg.runtime.AbstractRedG;

import java.time.ZonedDateTime;

public class DataSet {

    public static AbstractRedG getDataSet() {
        RedG redG = new RedG();
        redG.setPreparedStatementParameterSetter(new MyPreparedStatementParameterSetter());

        redG.addAppointment(redG.dummyPatient())
                .time(ZonedDateTime.now());

        return redG;
    }
}
