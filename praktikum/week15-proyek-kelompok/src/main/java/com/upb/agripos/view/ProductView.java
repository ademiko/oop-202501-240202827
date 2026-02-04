package com.upb.agripos.view;

import com.upb.agripos.model.Product;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ProductView extends VBox {
    // Input Fields (Dibuat public agar bisa diakses Controller)
    public TextField txtCode = new TextField();
    public TextField txtName = new TextField();
    public TextField txtCategory = new TextField();
    public TextField txtPrice = new TextField();
    public TextField txtStock = new TextField();
    
    // Buttons
    public Button btnSave = new Button("Simpan");
    public Button btnDelete = new Button("Hapus");
    
    // Table
    public TableView<Product> table = new TableView<>();

    public ProductView() {
        this.setPadding(new Insets(20));
        this.setSpacing(15);

        Label title = new Label("MANAJEMEN PRODUK");
        title.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");

        // 1. Form Input (GridPane)
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(new Label("Kode:"), 0, 0);
        grid.add(txtCode, 1, 0);
        
        grid.add(new Label("Nama:"), 0, 1);
        grid.add(txtName, 1, 1);
        
        grid.add(new Label("Kategori:"), 0, 2);
        grid.add(txtCategory, 1, 2);
        
        grid.add(new Label("Harga:"), 0, 3);
        grid.add(txtPrice, 1, 3);
        
        grid.add(new Label("Stok:"), 0, 4);
        grid.add(txtStock, 1, 4);

        // 2. Tombol Aksi
        HBox actions = new HBox(10, btnSave, btnDelete);
        
        // 3. Setup Tabel (Ini bagian yang tadi bikin kolom tidak muncul)
        setupTable();

        this.getChildren().addAll(title, grid, actions, table);
    }

    private void setupTable() {
        // Kolom Kode
        TableColumn<Product, String> colCode = new TableColumn<>("Kode");
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colCode.setMinWidth(100);

        // Kolom Nama
        TableColumn<Product, String> colName = new TableColumn<>("Nama");
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colName.setMinWidth(150);

        // Kolom Kategori (KOLOM BARU)
        TableColumn<Product, String> colCat = new TableColumn<>("Kategori");
        colCat.setCellValueFactory(new PropertyValueFactory<>("category"));
        colCat.setMinWidth(100);

        // Kolom Harga (KOLOM BARU)
        TableColumn<Product, Double> colPrice = new TableColumn<>("Harga");
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colPrice.setMinWidth(100);

        // Kolom Stok (KOLOM BARU)
        TableColumn<Product, Integer> colStock = new TableColumn<>("Stok");
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colStock.setMinWidth(80);

        // Masukkan semua kolom ke tabel
        table.getColumns().clear();
        table.getColumns().addAll(colCode, colName, colCat, colPrice, colStock);
        
        // Agar tabel memenuhi lebar layar
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }
}