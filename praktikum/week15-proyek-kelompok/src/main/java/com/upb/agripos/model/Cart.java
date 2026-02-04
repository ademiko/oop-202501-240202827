package com.upb.agripos.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items = new ArrayList<>();
    private DiscountStrategy discountStrategy = new NoDiscount(); // Default

    public void addItem(Product p, int qty) {
        items.add(new CartItem(p, qty));
    }

    public void setDiscountStrategy(DiscountStrategy ds) { this.discountStrategy = ds; }

    public double getTotalBeforeDiscount() {
        return items.stream().mapToDouble(CartItem::getSubtotal).sum();
    }

    public double getDiscountAmount() {
        return discountStrategy.calculateDiscount(getTotalBeforeDiscount());
    }

    public double getFinalTotal() {
        return getTotalBeforeDiscount() - getDiscountAmount();
    }

    public List<CartItem> getItems() { return items; }
    public void clear() { items.clear(); }
}