package com.upb.agripos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.upb.agripos.model.Product;

public class JdbcProductDAO implements ProductDAO {
    private final Connection connection;

    public JdbcProductDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Product p) throws Exception {
        // Menggunakan logika UPSERT: Jika kode sudah ada, maka update datanya
        String sql = "INSERT INTO products (code, name, category, price, stock) VALUES (?, ?, ?, ?, ?) " +
                     "ON CONFLICT (code) DO UPDATE SET " +
                     "name = EXCLUDED.name, category = EXCLUDED.category, " +
                     "price = EXCLUDED.price, stock = EXCLUDED.stock";
        
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, p.getCode());
            ps.setString(2, p.getName());
            ps.setString(3, p.getCategory()); // Mendukung kategori baru
            ps.setDouble(4, p.getPrice());
            ps.setInt(5, p.getStock());
            ps.executeUpdate();
        }
    }

    @Override
    public void update(Product p) throws Exception {
        // Method ini tetap diperlukan jika interface ProductDAO memintanya
        String sql = "UPDATE products SET name = ?, category = ?, price = ?, stock = ? WHERE code = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, p.getName());
            ps.setString(2, p.getCategory());
            ps.setDouble(3, p.getPrice());
            ps.setInt(4, p.getStock());
            ps.setString(5, p.getCode());
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(String code) throws Exception {
        String sql = "DELETE FROM products WHERE code = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, code);
            ps.executeUpdate();
        }
    }

    @Override
    public List<Product> findAll() throws Exception {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products ORDER BY code ASC";
        try (Statement st = connection.createStatement(); 
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Product(
                    rs.getString("code"),
                    rs.getString("name"),
                    rs.getString("category"), // Pastikan kolom category ada di tabel DB
                    rs.getDouble("price"),
                    rs.getInt("stock")
                ));
            }
        }
        return list;
    }

    @Override
    public Product findById(String code) throws Exception {
        String sql = "SELECT * FROM products WHERE code = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, code);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Product(
                        rs.getString("code"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getDouble("price"),
                        rs.getInt("stock")
                    );
                }
            }
        }
        return null;
    }
}