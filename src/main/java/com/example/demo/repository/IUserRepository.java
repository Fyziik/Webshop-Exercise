package com.example.demo.repository;

import com.example.demo.model.User;

import java.util.List;

public interface IUserRepository {

    boolean create(User m);

    User read(String username);

    List<User> readAll();

}
