package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import javax.sql.rowset.JdbcRowSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDBRepository implements IUserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SqlRowSet sqlRowSet;


    @Override
    public boolean create(User m) {
        String sql = "INSERT INTO user(username, password) VALUES ('" + m.getUsername() + "','" + m.getPassword() + "')";
        jdbcTemplate.execute(sql);
        return true;
    }

    @Override
    public User read(String username) {
        List<User> users = readAll();
        for (User user : users) {
            if (user.getUsername().toLowerCase().equals(username.toLowerCase())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> readAll() {
        String sql = "SELECT * FROM user";
        sqlRowSet = jdbcTemplate.queryForRowSet(sql);

        ArrayList<User> tmp = new ArrayList<>();

        while (sqlRowSet.next()) {
            tmp.add(new User(sqlRowSet.getString("username"), sqlRowSet.getString("password")));
        }

        return tmp;
    }
}
