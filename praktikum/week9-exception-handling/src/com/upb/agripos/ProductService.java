package com.upb.agripos;

// Singleton agar layanan manajemen produk hanya satu di aplikasi
public class ProductService {
    private static ProductService instance;

    private ProductService() {} // Private constructor

    public static ProductService getInstance() {
        if (instance == null) {
            instance = new ProductService();
        }
        return instance;
    }

    public void logAction(String msg) {
        System.out.println("[LOG SERVICE] " + msg);
    }
}