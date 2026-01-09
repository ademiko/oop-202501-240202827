package com.upb.agripos;

// Latihan: Exception untuk validasi stok gudang
public class InsufficientStockException extends Exception {
    public InsufficientStockException(String message) {
        super(message);
    }
}
