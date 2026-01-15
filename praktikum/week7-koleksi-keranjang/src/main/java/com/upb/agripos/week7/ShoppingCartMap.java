package com.upb.agripos.week7;

import java.util.HashMap;
import java.util.Map;

import com.upb.agripos.model.Product;

public class ShoppingCartMap {
    private final Map<Product, Integer> items = new HashMap<>();

    public void addProduct(Product p) {
        items.put(p, items.getOrDefault(p, 0) + 1);
    }

    // Latihan 1: Hitung Total dengan Pajak
    public double getTotalWithTax(double taxRate) {
        double total = getTotal();
        return total + (total * taxRate);
    }

    // Latihan 3: Kosongkan Keranjang
    public void emptyCart() {
        items.clear();
    }

    public double getTotal() {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }

    public void printCart() {
        System.out.println("\n--- Detail Keranjang (Map) ---");
        for (Map.Entry<Product, Integer> e : items.entrySet()) {
            System.out.println("- " + e.getKey().getName() + " x" + e.getValue() + " = Rp" + (e.getKey().getPrice() * e.getValue()));
        }
    }
}
