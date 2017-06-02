package com.btc.redg.example.mysql;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;

public class AppointmentTest {

    private static final Logger LOG = LoggerFactory.getLogger(AppointmentTest.class);

    @Test
    public void test() throws Exception {
        LOG.info("Preparing test data...");
        // Insert your database credentials here. Do not do this in production!
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/redg?useLegacyDatetimeCode=false&serverTimezone=Europe/Berlin",
                "redg", "redg");
        Statement stmt = connection.createStatement();
        stmt.execute("DELETE FROM redg.appointment");
        stmt.execute("DELETE FROM redg.patient");

        DataSets.getDataSet().insertDataIntoDatabase(connection);

        LOG.info("Running tests");

        ResultSet rs = stmt.executeQuery("SELECT count(*) FROM redg.appointment");
        rs.next();
        assertEquals(3L, rs.getLong(1));

    }
}
