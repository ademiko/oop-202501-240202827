package com.upb.agripos.service;

import java.util.List;

import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.model.Product;

public class ProductService {
    private ProductDAO dao;

    // Menerima interface DAO (Prinsip Dependency Inversion)
    public ProductService(ProductDAO dao) {
        this.dao = dao;
    }

    public void addProduct(Product p) throws Exception {
        // Validasi aturan bisnis (Bab 9 - Exception Handling)
        if (p.getCode().isEmpty() || p.getName().isEmpty()) {
            throw new Exception("Kode dan Nama produk tidak boleh kosong!");
        }
        if (p.getPrice() <= 0) {
            throw new Exception("Harga harus lebih dari 0!");
        }
        if (p.getStock() < 0) {
            throw new Exception("Stok tidak boleh negatif!");
        }
        
        // Kirim ke database melalui DAO
        dao.insert(p);
    }

    public void deleteProduct(String code) throws Exception {
        if (code == null || code.isEmpty()) {
            throw new Exception("Kode produk yang akan dihapus tidak valid.");
        }
        dao.delete(code);
    }

    public List<Product> getAllProducts() throws Exception {
        return dao.findAll();
    }
}