package com.upb.agripos.model;

public class Product {
    private String code;
    private String name;
    private String category; // BARU: Menambahkan kategori
    private double price;
    private int stock;
    private int quantity; // Untuk kebutuhan keranjang

    // UPDATE CONSTRUCTOR: Sekarang menerima 5 parameter utama
    public Product(String code, String name, String category, double price, int stock) {
        this.code = code;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.quantity = 0;
    }

    // Getter dan Setter
    public String getCode() { return code; }
    public String getName() { return name; }
    public String getCategory() { return category; } // Memperbaiki eror 'undefined getCategory'
    public void setCategory(String category) { this.category = category; }
    
    public double getPrice() { return price; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}