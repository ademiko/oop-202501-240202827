package com.upb.agripos;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private final Map<Product, Integer> items = new HashMap<>();

    // Latihan: Validasi tambah produk
    public void addProduct(Product p, int qty) throws InvalidQuantityException {
        if (qty <= 0) {
            throw new InvalidQuantityException("Gagal Tambah: Jumlah (" + qty + ") harus lebih dari 0!");
        }
        items.put(p, items.getOrDefault(p, 0) + qty);
        System.out.println("Berhasil menambah " + qty + " " + p.getName());
    }

    // Latihan: Validasi hapus produk
    public void removeProduct(Product p) throws ProductNotFoundException {
        if (!items.containsKey(p)) {
            throw new ProductNotFoundException("Gagal Hapus: Produk '" + p.getName() + "' tidak ditemukan di keranjang!");
        }
        items.remove(p);
        System.out.println("Berhasil menghapus " + p.getName() + " dari keranjang.");
    }

    // Latihan: Validasi checkout (Stok)
    public void checkout() throws InsufficientStockException {
        System.out.println("\n--- Memulai Proses Checkout ---");
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int qtyBelanja = entry.getValue();

            if (product.getStock() < qtyBelanja) {
                throw new InsufficientStockException("Checkout Gagal: Stok " + product.getName() + 
                    " sisa " + product.getStock() + ", tapi Anda ingin beli " + qtyBelanja);
            }
        }

        // Jika semua stok cukup, kurangi stok asli
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            entry.getKey().reduceStock(entry.getValue());
        }
        System.out.println("Checkout Berhasil! Stok gudang telah diperbarui.");
    }
}
