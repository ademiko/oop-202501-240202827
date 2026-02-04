package com.upb.agripos.dao;

import java.util.List;

import com.upb.agripos.model.Product;

public interface TransactionDAO {
    void insertTransaction(double total, List<Product> items, String method) throws Exception;
}