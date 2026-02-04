package com.upb.agripos.exception;

/**
 * Parent exception untuk semua error spesifik di aplikasi Agri-POS.
 */
public class AgriException extends Exception {
    public AgriException(String message) {
        super(message);
    }
}