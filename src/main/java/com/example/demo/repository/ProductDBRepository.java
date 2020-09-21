package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
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
        String desc = product.getDescription();
        String catagory = product.getCatagory();
        String sql = "INSERT INTO product (name, image, price, description, catagory) VALUES ('" + name + "','" + image + "','" + price + "','" + desc + "','" + catagory + "')";
        jdbcTemplate.execute(sql);
        return true;
    }

    @Override
    //'Search' method
    public List<Product> read(String keyword) {
        String sql = "SELECT * FROM product WHERE name LIKE '%" + keyword + "%'";
        sqlRowSet = jdbcTemplate.queryForRowSet(sql);

        ArrayList<Product> tmp = new ArrayList<>();
        while (sqlRowSet.next()) {
            tmp.add(load(sqlRowSet));
        }
        return tmp;
    }

    public List<Product> readCatagory(String keyword) {
        String sql = "SELECT * FROM product WHERE catagory LIKE '%" + keyword + "%'";
        sqlRowSet = jdbcTemplate.queryForRowSet(sql);

        ArrayList<Product> tmp = new ArrayList<>();
        while (sqlRowSet.next()) {
            tmp.add(load(sqlRowSet));
        }
        return tmp;
    }

    @Override
    public List<Product> readAll() {
        String sql = "SELECT * FROM product";
        sqlRowSet = jdbcTemplate.queryForRowSet(sql);

        ArrayList<Product> tmp = new ArrayList<>();
        while (sqlRowSet.next()) {
            tmp.add(load(sqlRowSet));
        }
        mixList(tmp);
        ArrayList<Product> list = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            list.add(tmp.get(i));
        }
        return list;
    }

    private List<Product> mixList(List<Product> list) {
        Collections.shuffle(list);
        return list;
    }

    @Override
    public Product findProductInDB(int id) {
        String sql = "SELECT * FROM product WHERE ID  = " + id;
        sqlRowSet = jdbcTemplate.queryForRowSet(sql);

        if (sqlRowSet.next()) {
            return load(sqlRowSet);
        }
        return null;
    }

    private Product load(SqlRowSet rs) {
        return new Product(
                rs.getString("name"),
                rs.getString("image"),
                rs.getInt("ID"),
                rs.getDouble("price"),
                rs.getString("description"),
                rs.getString("catagory")
        );
    }
}
