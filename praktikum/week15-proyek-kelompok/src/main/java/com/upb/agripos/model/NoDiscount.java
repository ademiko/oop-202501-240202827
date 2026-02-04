package com.upb.agripos.model;

public class NoDiscount implements DiscountStrategy {
    @Override public double calculateDiscount(double total) { return 0; }
    @Override public String getPromoName() { return "Tidak Ada Promo"; }
}