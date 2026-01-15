package com.upb.agripos.config;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private DatabaseConnection() {
        System.out.println("[DB] Menginisialisasi Koneksi Tunggal...");
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public void connect() {
        System.out.println("[DB] Terhubung ke Database Agri-POS.");
    }
}