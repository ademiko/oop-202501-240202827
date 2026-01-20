package com.upb.agripos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.upb.agripos.model.Product;
import com.upb.agripos.service.CartService;

public class CartServiceTest {
    @Test
    public void testCartTotal() throws Exception {
        CartService cs = new CartService();
        Product p = new Product("P01", "Bibit", 1000, 10);
        cs.addToCart(p, 2);
        assertEquals(2000.0, cs.getCart().getTotalPrice());
    }
}