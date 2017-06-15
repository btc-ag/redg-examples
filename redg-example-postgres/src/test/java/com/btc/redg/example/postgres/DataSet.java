package com.btc.redg.example.postgres;

import com.btc.redg.example.postgres.util.MyPreparedStatementParameterSetter;
import com.btc.redg.generated.RedG;
import com.btc.redg.runtime.AbstractRedG;

import java.time.ZonedDateTime;

public class DataSet {

    public static AbstractRedG getDataSet() {
        RedG redG = new RedG();
        redG.setPreparedStatementParameterSetter(new MyPreparedStatementParameterSetter());

        // NOTE: This currently fails due to a bug in the runtime. It will be fixed in version 1.0.18
        redG.addAppointment(redG.dummyPatient())
                .time(ZonedDateTime.now());

        return redG;
    }
}
