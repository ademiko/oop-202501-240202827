package com.upb.agripos;

import com.upb.agripos.controller.*;
import com.upb.agripos.service.*;
import com.upb.agripos.view.*;
import com.upb.agripos.dao.*;
import com.upb.agripos.util.DatabaseConnection;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AppJavaFX extends Application {
    private Stage stage;
    private ProductService ps;
    private CartService cs;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        
        // Inisialisasi Service (Hanya buat satu kali agar data sinkron)
        ps = new ProductService(new JdbcProductDAO(DatabaseConnection.get()));
        cs = new CartService(new JdbcTransactionDAO(DatabaseConnection.get()));

        showLogin();
    }

    private void showLogin() {
        LoginView lv = new LoginView();
        // Saat login sukses, jalankan showDashboard dengan role dari database
        new LoginController(lv, this::showDashboard);
        
        stage.setScene(new Scene(lv, 400, 350));
        stage.setTitle("Login Agri-POS");
        stage.show();
    }

    private void showDashboard(String role) {
        TransactionView kasirView = new TransactionView();
        AdminProductView adminView = new AdminProductView();

        new TransactionController(cs, ps, kasirView);
        new AdminProductController(ps, adminView);

        TabPane root = new TabPane();
        Tab tabKasir = new Tab("Kasir", kasirView);
        Tab tabAdmin = new Tab("Admin Produk", adminView);

        // LOGIKA ROLE-BASED ACCESS CONTROL (RBAC)
        if (role.equalsIgnoreCase("admin")) {
            // Admin bisa melihat kedua tab
            root.getTabs().addAll(tabKasir, tabAdmin);
        } else {
            // Kasir HANYA bisa melihat tab Kasir
            root.getTabs().add(tabKasir);
        }

        stage.setScene(new Scene(root, 1000, 700));
        stage.setTitle("Agri-POS Dashboard - Login as: " + role.toUpperCase());
        
        // Refresh data awal agar tabel langsung terisi
        try { ps.refreshSharedData(); } catch (Exception e) { e.printStackTrace(); }
    }

    public static void main(String[] args) { launch(args); }
}