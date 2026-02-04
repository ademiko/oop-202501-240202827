package com.upb.agripos.model;

public class CashPayment implements PaymentStrategy {
    @Override 
    public void pay(double amount) { System.out.println("Dibayar Tunai: Rp" + amount); }
    @Override 
    public String getMethodName() { return "Tunai"; }
}