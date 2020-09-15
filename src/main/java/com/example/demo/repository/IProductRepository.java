package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IProductRepository {

    boolean create(Product product);

    List<Product> read(String email);

    List<Product> readAll();

    Product findProductInDB(int id);
}
