package com.upb.agripos;

// Latihan: Exception jika produk tidak ada di keranjang
public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String message) {
        super(message);
    }
}