package com.upb.agripos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.upb.agripos.model.Product;

public class ProductTest {
    @Test
    public void testProductGetters() {
        Product p = new Product("A01", "Pupuk Cair");
        assertEquals("A01", p.getCode(), "Kode produk harus sesuai");
        assertEquals("Pupuk Cair", p.getName(), "Nama produk harus sesuai");
    }
}