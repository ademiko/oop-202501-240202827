package com.upb.agripos.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Consumer;

import com.upb.agripos.util.DatabaseConnection;
import com.upb.agripos.view.LoginView;

import javafx.scene.control.Alert;

public class LoginController {
    public LoginController(LoginView v, Consumer<String> onSuccess) {
        v.getBtnLogin().setOnAction(e -> {
            String user = v.getTxtUsername().getText();
            String pass = v.getTxtPassword().getText();

            // Query untuk mencocokkan user, pass, dan mengambil role
            String sql = "SELECT role FROM users WHERE username = ? AND password = ?";
            try (Connection conn = DatabaseConnection.get();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                
                ps.setString(1, user);
                ps.setString(2, pass);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    String role = rs.getString("role");
                    onSuccess.accept(role); // Kirim role ke Main.java
                } else {
                    new Alert(Alert.AlertType.ERROR, "Login Gagal! Akun tidak ditemukan.").show();
                }
            } catch (SQLException ex) {
                new Alert(Alert.AlertType.ERROR, "Koneksi Database Gagal!").show();
                ex.printStackTrace();
            }
        });
    }
}