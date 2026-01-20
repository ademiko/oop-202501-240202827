package com.upb.agripos.view;

import com.upb.agripos.model.Product;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PosView extends BorderPane {
    public TableView<Product> table = new TableView<>();
    public TextField txtCode = new TextField(), txtName = new TextField(), txtPrice = new TextField(), txtStock = new TextField(), txtQty = new TextField();
    public Button btnAddProd = new Button("Simpan Produk"), btnDeleteProd = new Button("Hapus"), btnAddToCart = new Button("Tambah ke Keranjang");
    public ListView<String> cartList = new ListView<>();
    public Label lblTotal = new Label("Total: Rp 0");

    public PosView() {
        this.setPadding(new Insets(10));
        
        // --- PANEL KIRI: CRUD PRODUK ---
        VBox leftPanel = new VBox(10);
        GridPane grid = new GridPane();
        grid.setHgap(5); grid.setVgap(5);
        grid.add(new Label("Kode:"), 0, 0); grid.add(txtCode, 1, 0);
        grid.add(new Label("Nama:"), 0, 1); grid.add(txtName, 1, 1);
        grid.add(new Label("Harga:"), 0, 2); grid.add(txtPrice, 1, 2);
        grid.add(new Label("Stok:"), 0, 3); grid.add(txtStock, 1, 3);
        
        TableColumn<Product, String> c1 = new TableColumn<>("Kode");
        c1.setCellValueFactory(new PropertyValueFactory<>("code"));
        TableColumn<Product, String> c2 = new TableColumn<>("Nama");
        c2.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        table.getColumns().addAll(c1, c2);
        leftPanel.getChildren().addAll(new Label("MASTER DATA"), grid, new HBox(5, btnAddProd, btnDeleteProd), table);

        // --- PANEL KANAN: KASIR/KERANJANG ---
        VBox rightPanel = new VBox(10);
        rightPanel.setPadding(new Insets(0, 0, 0, 20));
        txtQty.setPromptText("Qty");
        rightPanel.getChildren().addAll(new Label("KASIR"), new HBox(5, new Label("Qty:"), txtQty, btnAddToCart), new Label("Isi Keranjang:"), cartList, lblTotal);

        this.setLeft(leftPanel);
        this.setCenter(rightPanel);
    }
}