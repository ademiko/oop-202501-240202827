package com.upb.agripos.service;

import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.model.Product;
import java.util.List;

public class ProductService {
    private ProductDAO dao;

    public ProductService(ProductDAO dao) {
        this.dao = dao;
    }

    public void addProduct(Product p) throws Exception {
        // Validasi Bisnis sederhana
        if (p.getCode().isEmpty() || p.getName().isEmpty()) {
            throw new Exception("Kode dan Nama produk tidak boleh kosong!");
        }
        if (p.getPrice() <= 0) {
            throw new Exception("Harga harus lebih besar dari 0!");
        }
        
        // Panggil DAO untuk simpan ke Database
        dao.insert(p);
    }

    public List<Product> getAllProducts() throws Exception {
        return dao.findAll();
    }
}