package com.upb.agripos.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items = new ArrayList<>();

    public void addItem(Product p, int qty) {
        items.add(new CartItem(p, qty));
    }

    public List<CartItem> getItems() { return items; }

    public double getTotalPrice() {
        return items.stream().mapToDouble(CartItem::getSubtotal).sum();
    }

    public void clear() { items.clear(); }
}