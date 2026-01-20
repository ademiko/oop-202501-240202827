package com.upb.agripos;

import com.upb.agripos.controller.PosController;
import com.upb.agripos.dao.DBConnection;
import com.upb.agripos.dao.JdbcProductDAO;
import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.service.CartService;
import com.upb.agripos.service.ProductService;
import com.upb.agripos.view.PosView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppJavaFX extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("Hello World, I am Ade Miko - [240202827]");
        
        ProductDAO dao = new JdbcProductDAO(DBConnection.getConnection());
        ProductService ps = new ProductService(dao);
        CartService cs = new CartService();
        PosView view = new PosView();
        new PosController(ps, cs, view);

        stage.setScene(new Scene(view, 700, 500));
        stage.setTitle("Agri-POS Final");
        stage.show();
    }
    public static void main(String[] args) { launch(args); }
}