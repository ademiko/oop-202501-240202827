package com.upb.agripos.model;

public class EWalletPayment implements PaymentStrategy {
    @Override 
    public void pay(double amount) { System.out.println("Dibayar E-Wallet: Rp" + amount); }
    @Override 
    public String getMethodName() { return "E-Wallet"; }
}