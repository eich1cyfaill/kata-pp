package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static final String dbUrl = "jdbc:mysql://localhost:3306/katapp";
    private static final String dbUsername = "root";
    private static final String dbPassword = "root";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
    }

}
