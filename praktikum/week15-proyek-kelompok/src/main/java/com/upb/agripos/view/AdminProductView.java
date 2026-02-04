package com.upb.agripos.view;

import com.upb.agripos.model.Product;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AdminProductView extends VBox {
    private TableView<Product> adminTable;
    private TextField txtCode, txtName, txtKategori, txtPrice, txtStock; // TAMBAH: txtKategori
    private Button btnSimpan, btnHapus;

    public AdminProductView() {
        setPadding(new Insets(15));
        setSpacing(10);

        adminTable = new TableView<>();
        setupTable(); 

        GridPane form = new GridPane();
        form.setHgap(10); form.setVgap(10);
        
        txtCode = new TextField(); 
        txtName = new TextField();
        txtKategori = new TextField(); // INISIALISASI: txtKategori
        txtPrice = new TextField(); 
        txtStock = new TextField();
        
        // Menambahkan inputan Kategori ke baris ke-2
        form.addRow(0, new Label("Kode:"), txtCode);
        form.addRow(1, new Label("Nama:"), txtName);
        form.addRow(2, new Label("Kategori:"), txtKategori); // FITUR BARU
        form.addRow(3, new Label("Harga:"), txtPrice);
        form.addRow(4, new Label("Stok:"), txtStock);

        btnSimpan = new Button("Simpan/Update Produk");
        btnSimpan.setStyle("-fx-background-color: #2980b9; -fx-text-fill: white;");
        btnHapus = new Button("Hapus");

        getChildren().addAll(
            new Label("Manajemen Stok (Admin):"), 
            adminTable, 
            form, 
            new HBox(10, btnSimpan, btnHapus)
        );
    }

    private void setupTable() {
        TableColumn<Product, String> cCol = new TableColumn<>("Kode");
        cCol.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getCode()));

        TableColumn<Product, String> nCol = new TableColumn<>("Produk");
        nCol.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getName()));

        // BARU: Kolom Kategori di Tabel
        TableColumn<Product, String> catCol = new TableColumn<>("Kategori");
        catCol.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().getCategory()));

        TableColumn<Product, Double> hCol = new TableColumn<>("Harga");
        hCol.setCellValueFactory(d -> new javafx.beans.property.SimpleObjectProperty<>(d.getValue().getPrice()));

        TableColumn<Product, Integer> sCol = new TableColumn<>("Stok");
        sCol.setCellValueFactory(d -> new javafx.beans.property.SimpleObjectProperty<>(d.getValue().getStock()));

        adminTable.getColumns().addAll(cCol, nCol, catCol, hCol, sCol); // Tambahkan catCol ke tabel
    }

    // GETTERS: Disesuaikan agar sinkron dengan Controller
    public TableView<Product> getAdminTable() { return adminTable; }
    public Button getBtnSimpan() { return btnSimpan; }
    public Button getBtnHapus() { return btnHapus; } // Tambah Getter Hapus
    
    // Nama method disamakan dengan error log: getTxtKode, getTxtNama, dll.
    public TextField getTxtKode() { return txtCode; }
    public TextField getTxtNama() { return txtName; }
    public TextField getTxtKategori() { return txtKategori; } // Getter Kategori
    public TextField getTxtHarga() { return txtPrice; }
    public TextField getTxtStok() { return txtStock; }
}