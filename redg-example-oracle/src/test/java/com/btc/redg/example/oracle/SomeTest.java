package com.btc.redg.example.oracle;

import com.btc.redg.generated.GClass;
import com.btc.redg.generated.GStudent;
import com.btc.redg.generated.RedG;
import com.btc.redg.runtime.defaultvalues.pluggable.*;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

/**
 * A test that only inserts some data into the database and checks that they were inserted. Take a look at the content of your
 * db to see what exactly got inserted.
 */
public class SomeTest {

    private static Connection connection;

    @BeforeClass
    public static void insertData() throws Exception {
        Class.forName("oracle.jdbc.OracleDriver");
        // Insert your username/password here
        // DO NOT USE PRODUCTION CREDENTIALS OR PUBLISH THESE ANYWHERE. These are examples, not best-practice approaches!
        connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "user", "password");
        Statement statement = connection.createStatement();
        statement.execute("DELETE FROM REDGDEMO.STUDENTS_IN_CLASSES");
        statement.execute("DELETE FROM REDGDEMO.STUDENT");
        statement.execute("DELETE FROM REDGDEMO.CLASS");

        RedG redG = new RedG();

        PluggableDefaultValueStrategy strategy = new PluggableDefaultValueStrategy.Builder()
                .use(new IncrementingNumberProvider())
                .use(new StaticDateProvider(new Date(1072273332000L)))
                .useDefault()
                .build();
        redG.setDefaultValueStrategy(strategy);

        List<GStudent> students = IntStream.range(1, 11).mapToObj(num -> redG.addStudent()
                .firstName("Mark")
                .lastName("Johnson the " + num + ".")).collect(Collectors.toList());
        List<GClass> classes = IntStream.range(1, 4).mapToObj(num -> redG.addClass()
                .name("Class " + num)).collect(Collectors.toList());
        students.forEach(s ->
            classes.forEach(s::addStudentsInClassesRelation)
        );

        redG.addStudent()
                .firstName("Max")
                .lastName("Mustermann")
                .studentId(1337L); // Due to custom mappings the id is a long and not BigDecimal.
        redG.
        RedG redG1 = new RedG();
        redG.insertDataIntoDatabase(connection);

    }

    @Test
    public void checkData() throws Exception {
        Statement statement = connection.createStatement();
        ResultSet students = statement.executeQuery("SELECT COUNT(*) FROM REDGDEMO.STUDENT");
        students.next();
        assertEquals(students.getLong(1), 11L);

        ResultSet classes = statement.executeQuery("SELECT COUNT(*) FROM REDGDEMO.CLASS");
        classes.next();
        assertEquals(classes.getLong(1), 3L);

        ResultSet joinTable = statement.executeQuery("SELECT COUNT(*) FROM REDGDEMO.STUDENTS_IN_CLASSES");
        joinTable.next();
        assertEquals(joinTable.getLong(1), 30L);
    }
}
