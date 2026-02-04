package com.upb.agripos.model;

public interface DiscountStrategy {
    double calculateDiscount(double total);
    String getPromoName();
}