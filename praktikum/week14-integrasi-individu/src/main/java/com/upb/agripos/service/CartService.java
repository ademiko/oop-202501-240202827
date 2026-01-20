package com.upb.agripos.service;

import com.upb.agripos.model.Cart;
import com.upb.agripos.model.Product;

public class CartService {
    private Cart cart = new Cart();

    public void addToCart(Product p, int qty) throws Exception {
        if (qty <= 0) throw new Exception("Kuantitas harus > 0");
        if (qty > p.getStock()) throw new Exception("Stok tidak cukup!");
        cart.addItem(p, qty);
    }

    public Cart getCart() { return cart; }
}