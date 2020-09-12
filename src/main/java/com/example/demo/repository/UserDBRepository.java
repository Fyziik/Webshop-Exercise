package com.example.demo.repository;

import com.example.demo.model.User;

import java.util.List;

public class UserDBRepository implements IUserRepository {

    @Override
    public boolean create(User m) {
        return false;
    }

    @Override
    public User read(String email) {
        return null;
    }

    @Override
    public List<User> readAll() {
        return null;
    }
}
