package com.vistext.letstextapp.externals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresDBConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/your_database";
    private static final String USERNAME = "your_username";
    private static final String PASSWORD = "your_password";

    private static Connection connection;

    private PostgresDBConnection() {
        // Private constructor to prevent instantiation
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
