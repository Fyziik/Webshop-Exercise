package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserArraylistRepository implements IUserRepository {

    ArrayList<User> users;


    public UserArraylistRepository() {
        this.users = new ArrayList<>();
        this.users.add(new User("Fyziik", "1234"));
        this.users.add(new User("Test", "Test"));
        this.users.add(new User("Kaj", "Kaj"));
    }


    @Override
    public boolean create(User m) {
        return this.users.add(m);
    }

    @Override
    public User read(String username) {
        for (User user : users) {
            if (user.getUsername().toLowerCase().equals(username.toLowerCase())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> readAll() {
        return this.users;
    }
}
