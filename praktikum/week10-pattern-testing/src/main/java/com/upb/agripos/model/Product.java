package com.upb.agripos.model;

public class Product {
    private final String code, name;
    public Product(String code, String name) { this.code = code; this.name = name; }
    public String getCode() { return code; }
    public String getName() { return name; }

    public Integer getPrice() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}