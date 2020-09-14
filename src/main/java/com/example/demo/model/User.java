package com.example.demo.model;

public class User {

    private String username;
    private String password;
    //Something to represent a users cart, for when you have to load it

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    //Getters & Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
