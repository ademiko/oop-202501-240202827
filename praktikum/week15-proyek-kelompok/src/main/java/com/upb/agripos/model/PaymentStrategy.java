package com.upb.agripos.model;

public interface PaymentStrategy {
    void pay(double amount);
    String getMethodName();
}