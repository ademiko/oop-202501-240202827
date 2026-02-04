package com.upb.agripos.model;

public class PercentageDiscount implements DiscountStrategy {
    private double percent;
    public PercentageDiscount(double percent) { this.percent = percent; }

    @Override 
    public double calculateDiscount(double total) { return total * (percent / 100); }
    @Override 
    public String getPromoName() { return "Diskon " + percent + "%"; }
}