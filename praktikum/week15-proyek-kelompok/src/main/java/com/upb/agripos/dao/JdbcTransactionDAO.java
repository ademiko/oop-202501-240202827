package com.upb.agripos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import com.upb.agripos.model.Product;

public class JdbcTransactionDAO implements TransactionDAO {
    private final Connection connection;

    public JdbcTransactionDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insertTransaction(double total, List<Product> items, String method) throws Exception {
        // 1. Matikan Auto-Commit agar data terkirim secara utuh
        connection.setAutoCommit(false); 
        
        String sqlHeader = "INSERT INTO transactions (total_amount, payment_method) VALUES (?, ?)";
        String sqlDetail = "INSERT INTO transaction_items (transaction_id, product_code, quantity, price_at_transaction) VALUES (?, ?, ?, ?)";
        String sqlUpdateStock = "UPDATE products SET stock = stock - ? WHERE code = ?";

        try {
            int tId = 0;

            // 2. Simpan Header & Ambil ID Nota
            try (PreparedStatement psH = connection.prepareStatement(sqlHeader, Statement.RETURN_GENERATED_KEYS)) {
                psH.setDouble(1, total);
                psH.setString(2, method); // 'Tunai' atau 'QRIS'
                psH.executeUpdate();
                
                ResultSet rs = psH.getGeneratedKeys();
                if (rs.next()) tId = rs.getInt(1);
            }

            // 3. Simpan Detail & Potong Stok (Batch Processing)
            try (PreparedStatement psD = connection.prepareStatement(sqlDetail);
                 PreparedStatement psU = connection.prepareStatement(sqlUpdateStock)) {
                
                for (Product p : items) {
                    // Masukkan ke detail item transaksi
                    psD.setInt(1, tId);
                    psD.setString(2, p.getCode());
                    psD.setInt(3, p.getQuantity()); // Menggunakan jumlah beli asli
                    psD.setDouble(4, p.getPrice());
                    psD.addBatch();

                    // Kurangi stok di database pgAdmin
                    psU.setInt(1, p.getQuantity()); 
                    psU.setString(2, p.getCode());
                    psU.addBatch();
                }
                psD.executeBatch();
                psU.executeBatch();
            }

            // 4. Ritual Paling Penting: COMMIT agar data muncul di pgAdmin
            connection.commit(); 
            System.out.println("Suksess! Data sudah masuk ke pgAdmin.");

        } catch (Exception e) {
            // Jika ada satu barang yang eror, batalkan semua perubahan
            connection.rollback(); 
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}