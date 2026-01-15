package com.upb.agripos.week7;

import java.util.ArrayList;

import com.upb.agripos.model.Product;


public class ShoppingCart {
    private final ArrayList<Product> items = new ArrayList<>();

    public void addProduct(Product p) {
        items.add(p);
    }

    // Latihan 2: Cari Produk berdasarkan Kode
    public Product findProductByCode(String code) {
        for (Product p : items) {
            if (p.getCode().equalsIgnoreCase(code)) {
                return p;
            }
        }
        return null;
    }

    // Latihan 3: Kosongkan Keranjang
    public void emptyCart() {
        items.clear();
        System.out.println("Keranjang ArrayList telah dikosongkan.");
    }

    public double getTotal() {
        double sum = 0;
        for (Product p : items) {
            sum += p.getPrice();
        }
        return sum;
    }

    public void printCart() {
        System.out.println("Isi Keranjang (ArrayList): " + items.size() + " item.");
    }
}
