package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDBRepository implements IProductRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SqlRowSet sqlRowSet;


    @Override
    public boolean create(Product product) {
        String name = product.getName();
        String image = product.getImage();
        double price = product.getPrice();
        String sql = "INSERT INTO product (name, image, price) VALUES ('" + name + "','" + image + "','" + price + "')";
        jdbcTemplate.execute(sql);
        return true;
    }

    @Override
    public List<Product> read(String keyword) {
        String sql = "SELECT * FROM product WHERE name LIKE '%" + keyword + "%'";
        sqlRowSet = jdbcTemplate.queryForRowSet(sql);

        ArrayList<Product> tmp = new ArrayList<>();
        while (sqlRowSet.next()) {
            tmp.add(new Product(sqlRowSet.getString("name" ), sqlRowSet.getString("image"),
                    sqlRowSet.getInt("ID"), sqlRowSet.getDouble("price")));
        }
        return tmp;
    }

    @Override
    public List<Product> readAll() {
        String sql = "SELECT * FROM product";
        sqlRowSet = jdbcTemplate.queryForRowSet(sql);

        ArrayList<Product> tmp = new ArrayList<>();
        while (sqlRowSet.next()) {
            tmp.add(new Product(sqlRowSet.getString("name" ), sqlRowSet.getString("image"),
                    sqlRowSet.getInt("ID"), sqlRowSet.getDouble("price")));
        }
        return tmp;
    }

    @Override
    public Product findProductInDB(int id) {
        String sql = "SELECT * FROM product WHERE ID  = " + id;
        sqlRowSet = jdbcTemplate.queryForRowSet(sql);

        if (sqlRowSet.next()) {
            return new Product(
              sqlRowSet.getString("name"),
              sqlRowSet.getString("image"),
              sqlRowSet.getInt("ID"),
              sqlRowSet.getDouble("price")
            );
        }
        return null;
    }
}
