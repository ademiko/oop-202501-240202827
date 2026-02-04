package com.upb.agripos.view;

import com.upb.agripos.controller.ProductController;
import com.upb.agripos.controller.TransactionController;
import com.upb.agripos.service.CartService;
import com.upb.agripos.service.ProductService;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

public class MainDashboard extends BorderPane {
    
    public MainDashboard(String role, ProductService productService, CartService cartService) {
        TabPane tabs = new TabPane();
        
        // 1. Setup Tab Kasir
        TransactionView transView = new TransactionView();
        
        // SEKARANG KITA KIRIM 3 BAHAN: cartService, productService, dan transView
        new TransactionController(cartService, productService, transView); 
        
        Tab t1 = new Tab("Kasir", transView);
        t1.setClosable(false);
        tabs.getTabs().add(t1);

        // 2. Setup Tab Admin (Hanya jika login sebagai ADMIN)
        if (role.equalsIgnoreCase("ADMIN")) {
            ProductView prodView = new ProductView();
            new ProductController(productService, prodView);
            
            Tab t2 = new Tab("Admin Produk", prodView);
            t2.setClosable(false);
            tabs.getTabs().add(t2);
        }

        this.setCenter(tabs);
    }
}