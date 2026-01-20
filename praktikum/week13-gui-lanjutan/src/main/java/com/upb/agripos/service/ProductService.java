package com.upb.agripos.service;

import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.model.Product;
import java.util.List;

public class ProductService {
    private ProductDAO dao;

    public ProductService(ProductDAO dao) {
        this.dao = dao;
    }

    public void addProduct(Product p) throws Exception {
        dao.insert(p);
    }

    public void removeProduct(String code) throws Exception {
        dao.delete(code);
    }

    public List<Product> getAllProducts() throws Exception {
        return dao.findAll();
    }
}