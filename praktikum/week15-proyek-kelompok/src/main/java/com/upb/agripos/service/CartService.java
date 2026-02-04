package com.upb.agripos.service;

import java.util.ArrayList;
import java.util.List;

import com.upb.agripos.dao.TransactionDAO;
import com.upb.agripos.model.Product;

public class CartService {
    private final TransactionDAO transactionDAO;
    
    // TAMBAHAN: List untuk menyimpan barang yang ada di keranjang saat ini
    private final List<Product> cartItems = new ArrayList<>();
    private final List<Integer> itemQuantities = new ArrayList<>();

    public CartService(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }

    // FITUR LAMA (TETAP ADA): Mengirim data ke database
    public void processTransaction(double total, List<Product> items, String method) throws Exception {
        if (items == null || items.isEmpty()) {
            throw new Exception("Keranjang belanja masih kosong!");
        }
        transactionDAO.insertTransaction(total, items, method);
    }

    // PERBAIKAN 1: Logika menambah barang ke keranjang
    public void addToCart(Product p1, int qty) {
        if (p1 != null && qty > 0) {
            cartItems.add(p1);
            itemQuantities.add(qty);
        }
    }

    // PERBAIKAN 2: Logika menghitung total sebelum diskon
    public double getTotalBeforeDiscount() {
        double total = 0;
        for (int i = 0; i < cartItems.size(); i++) {
            // Rumus: Harga x Jumlah
            total += cartItems.get(i).getPrice() * itemQuantities.get(i);
        }
        return total;
    }

    // TAMBAHAN: Biar gampang ambil list barang saat mau checkout
    public List<Product> getCartItems() {
        return cartItems;
    }
}