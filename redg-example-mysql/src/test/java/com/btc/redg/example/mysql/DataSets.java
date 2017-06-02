package com.btc.redg.example.mysql;

import com.btc.redg.generated.GPatient;
import com.btc.redg.generated.RedG;
import com.btc.redg.runtime.AbstractRedG;

import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class DataSets {

    public static AbstractRedG getDataSet() {
        RedG redG = new RedG();

        GPatient patient = redG.addPatient()
                .patientId(42)
                .dateOfBirth(new Timestamp(258681600000L));

        redG.addAppointment(patient)
                .time(new Timestamp(1516466700000L)); // The "normal" way, not really human-readable

        redG.addAppointment(patient)
                .time("2018-04-15T14:00:00.000Z") // With the String-to-date converter
                .note("Just regular check-up");

        redG.addAppointment(patient)
                .time(ZonedDateTime.of(2018, 2, 17, 10, 30, 0, 0, ZoneOffset.UTC)); // With the custom converter

        return redG;
    }
}
