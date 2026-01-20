package com.upb.agripos.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static Connection connection;

    public static Connection getConnection() throws Exception {
        if (connection == null || connection.isClosed()) {
            // Ganti password sesuai dengan PostgreSQL kamu
            connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/agripos", "postgres", "1312"
            );
        }
        return connection;
    }
}