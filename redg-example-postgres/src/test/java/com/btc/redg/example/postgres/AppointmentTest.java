package com.btc.redg.example.postgres;

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
        LOG.info("Inserting test data...");
        // Insert login data here. Do not do this in production
        final Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/redg", "redg", "redg");
        final Statement stmt = connection.createStatement();
        stmt.execute("DELETE FROM redg.appointment");
        stmt.execute("DELETE FROM redg.patient");

        DataSet.getDataSet().insertDataIntoDatabase(connection);

        LOG.info("Testing...");

        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM redg.appointment");
        rs.next();
        assertEquals(1L, rs.getLong(1));
    }
}
