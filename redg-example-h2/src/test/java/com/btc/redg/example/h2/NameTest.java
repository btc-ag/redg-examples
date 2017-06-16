package com.btc.redg.example.h2;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class NameTest {

    private static final Logger LOG = LoggerFactory.getLogger(NameTest.class);

    private static Connection connection;

    @BeforeClass
    public static void prepareTest() throws Exception {
        LOG.info("Connecting to database...");
        Class.forName("org.h2.Driver");
        connection = DriverManager.getConnection("jdbc:h2:file:./database");

        LOG.info("Inserting data with RedG...");
        DataSets.getNameTestDataSet().insertDataIntoDatabase(connection);
    }

    @Test
    public void testNameEqual() throws Exception {
        LOG.info("Querying database...");
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT CUSTOMER.FIRST_NAME, CUSTOMER.LAST_NAME, CREDIT_CARD.CARD_HOLDER FROM CUSTOMER JOIN CREDIT_CARD ON CUSTOMER.CREDIT_CARD = CREDIT_CARD.CARD_NUMBER");
        LOG.info("Checking results...");
        while (rs.next()) {
            String firstName = rs.getString(1);
            String lastName = rs.getString(2);
            String holderName = rs.getString(3);
            LOG.info("Comparing customer {} {} with card holder {}", firstName, lastName, holderName);
            assertEquals(firstName + " " + lastName, holderName);
        }
    }

    @Test
    public void testVisualization() throws Exception {
        LOG.info("testing visualization...");
        final String vis = DataSets.getNameTestDataSet().getVisualizationJson();
        assertNotNull(vis);
        LOG.info(vis);
    }
}
