package com.example.demo.repository;

import com.example.demo.model.Cart;
import com.example.demo.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductArraylistRepository implements IProductRepository {

    ArrayList<Product> products = new ArrayList<>();

    public ProductArraylistRepository() {
        this.products.add(new Product("Apple", "/images/apple.png", 1, 5));
        this.products.add(new Product("Kiwi", "/images/kiwi.png", 2, 500));
        this.products.add(new Product("Pear", "/images/pear.png", 3, 15));
        this.products.add(new Product("Apple", "/images/apple.png", 1, 5));
    }

    @Override
    public boolean create(Product product) {
        return false;
    }

    @Override
    //Will suffice as 'search' function
    public List<Product> read(String keyword) {
        ArrayList<Product> tmp = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().toLowerCase().contains(keyword.toLowerCase())) {
                tmp.add(product);
            }
        }
        return tmp;
    }

    public Product findProductInDB(int id) {
        for (Product product : products) {
            if (product.getID() == id) {
                return product;
            }
        }
        return null;
    }

    @Override
    public List<Product> readAll() {
        return this.products;
    }
}
