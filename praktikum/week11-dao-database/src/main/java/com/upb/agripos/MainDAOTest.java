package com.upb.agripos;

import java.sql.Connection;
import java.sql.DriverManager;

import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.dao.ProductDAOImpl;
import com.upb.agripos.model.Product;

public class MainDAOTest {
    public static void main(String[] args) throws Exception {
        // 1. Koneksi ke database (Ganti password sesuai PostgreSQL kamu)
        Connection conn = DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/agripos", "postgres", "1312"
        );

        ProductDAO dao = new ProductDAOImpl(conn);

        // 2. CREATE (Tambah Data)  
        System.out.println("Menambah produk...");
        dao.insert(new Product("P01", "Pupuk Organik", 25000, 10));

        // 3. READ & UPDATE (Cari dan Ubah)
        Product p = dao.findByCode("P01");
        if (p != null) {
            System.out.println("Data ditemukan: " + p.getName());
            p.setName("Pupuk Organik Premium");
            dao.update(p);
            System.out.println("Data berhasil di-update!");
        }

        // 4. DELETE (Hapus)
        // dao.delete("P01"); 

        conn.close();
        System.out.println("Koneksi ditutup.");
    }
}