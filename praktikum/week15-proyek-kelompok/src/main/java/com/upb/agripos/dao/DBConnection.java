package com.upb.agripos.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static Connection connection;

    public static Connection getConnection() throws Exception {
        if (connection == null || connection.isClosed()) {
            // Sesuaikan password "1234" dengan password PostgreSQL kamu
            String url = "jdbc:postgresql://localhost:5432/agripos";
            String user = "postgres";
            String password = "1312";
            connection = DriverManager.getConnection(url, user, password);
        }
        return connection;
    }
}