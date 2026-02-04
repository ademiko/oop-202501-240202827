package com.upb.agripos.dao;

import java.util.List;

import com.upb.agripos.model.Product;

public interface ProductDAO {
    void save(Product p) throws Exception;
    void update(Product p) throws Exception;
    void delete(String code) throws Exception;
    List<Product> findAll() throws Exception;
    Product findById(String code) throws Exception;
}