package com.example.demo.repository;

import com.example.demo.model.Product;

import java.util.List;

public interface IProductRepository {

    boolean create(Product product);

    List<Product> read(String email);

    List<Product> readAll();
}
