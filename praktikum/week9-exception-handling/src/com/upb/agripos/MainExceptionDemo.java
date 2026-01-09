package com.upb.agripos;

public class MainExceptionDemo {
    public static void main(String[] args) {
        System.out.println("Hello, I am Ade Miko - 2420202827 ");
        System.out.println("===========================================");

        // Menggunakan Singleton Service
        ProductService logger = ProductService.getInstance();
        ShoppingCart cart = new ShoppingCart();
        
        // Buat produk dengan stok terbatas (hanya 3)
        Product pupuk = new Product("P01", "Pupuk Organik", 25000, 3);

        // 1. TEST: Invalid Quantity (Angka Negatif)
        try {
            logger.logAction("Mencoba tambah produk dengan qty negatif...");
            cart.addProduct(pupuk, -5);
        } catch (InvalidQuantityException e) {
            System.err.println("Exception Tertangkap: " + e.getMessage());
        }

        // 2. TEST: Product Not Found
        try {
            logger.logAction("Mencoba hapus produk yang belum ada...");
            cart.removeProduct(pupuk);
        } catch (ProductNotFoundException e) {
            System.err.println("Exception Tertangkap: " + e.getMessage());
        }

        // 3. TEST: Insufficient Stock (Beli 10 padahal stok cuma 3)
        try {
            logger.logAction("Mencoba checkout melebihi stok...");
            cart.addProduct(pupuk, 10);
            cart.checkout();
        } catch (InvalidQuantityException | InsufficientStockException e) {
            System.err.println("Exception Tertangkap: " + e.getMessage());
        } finally {
            System.out.println("\n[Finally] Proses validasi selesai dilakukan.");
        }
    }
}