package com.upb.agripos.service;

import java.util.List;

import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.model.Product;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProductService {
    private final ProductDAO productDAO;
    private final ObservableList<Product> sharedProducts = FXCollections.observableArrayList();

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    // FITUR LAMA: Tetap dipertahankan untuk sinkronisasi otomatis
    public void refreshSharedData() throws Exception {
        sharedProducts.setAll(productDAO.findAll());
    }

    public ObservableList<Product> getSharedProducts() {
        return sharedProducts;
    }

    // PERBAIKAN 1: Mengambil semua data produk dari DAO
    public List<Product> getAllProducts() throws Exception {
        return productDAO.findAll();
    }

    // PERBAIKAN 2: Logika menambah produk ke Database & update tampilan
    public void addProduct(Product p) throws Exception {
        // Simpan ke PostgreSQL melalui DAO
        productDAO.save(p); 
        
        // PENTING: Memicu refresh agar tabel di semua tab langsung terupdate
        refreshSharedData(); 
    }

    // PERBAIKAN 3: Logika menghapus produk berdasarkan kode unik
    public void deleteProduct(String code) throws Exception {
        // Hapus data di database
        productDAO.delete(code); 
        
        // Update tampilan secara real-time
        refreshSharedData();
    }
}