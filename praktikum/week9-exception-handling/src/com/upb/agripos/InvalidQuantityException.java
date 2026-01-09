package com.upb.agripos;

// Latihan: Exception untuk validasi input angka
public class InvalidQuantityException extends Exception {
    public InvalidQuantityException(String message) {
        super(message);
    }
}