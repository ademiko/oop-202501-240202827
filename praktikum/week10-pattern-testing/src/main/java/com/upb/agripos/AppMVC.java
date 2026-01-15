package com.upb.agripos;

import com.upb.agripos.model.Product;
import com.upb.agripos.view.ConsoleView;
import com.upb.agripos.controller.ProductController;
import com.upb.agripos.config.DatabaseConnection;

public class AppMVC {
    public static void main(String[] args) {
        System.out.println("Hello, I am Ade Miko - 2420202827 (Week 10)");
        
        // Singleton Test
        DatabaseConnection.getInstance().connect();

        // MVC Integration
        Product myProduct = new Product("P001", "Benih Jagung Hibrida");
        ConsoleView myView = new ConsoleView();
        ProductController myController = new ProductController(myProduct, myView);

        myController.updateView();
    }
}