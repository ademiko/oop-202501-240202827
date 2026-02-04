package com.upb.agripos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.upb.agripos.model.Product;
import com.upb.agripos.service.CartService;

public class CartServiceTest {
    private CartService service;

    @BeforeEach
    void setUp() {
        // DAO diset null karena kita hanya mengetes logika matematika di Service
        service = new CartService(null);
    }

    @Test
    void testAddToCartAndTotal() {
        // Membuat produk contoh (Kode, Nama, Kategori, Harga, Stok)
        Product p1 = new Product("P01", "Pupuk", "Pupuk", 10000.0, 10);
        
        // Mengetes fungsi addToCart
        service.addToCart(p1, 2); 
        
        // Verifikasi: 10000 * 2 harus sama dengan 20000
        double total = service.getTotalBeforeDiscount();
        assertEquals(20000.0, total, "Total harga harus sesuai dengan jumlah barang");
    }
}