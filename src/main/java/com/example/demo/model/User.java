package com.example.demo.model;

import java.util.Map;

public class User {

    private String username;
    private String password;
    //Something to represent a users cart, for when you have to load it
    Cart cart;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        retrieveCart(username);
    }

    private void retrieveCart(String username) {
        //TODO remake method so that it retrieves products from DB via username / ID for a username, instead of from arraylist
        //ProductArraylistRepository par = new ProductArraylistRepository();
        //this.cart = new Cart((ArrayList)par.readAll());
        this.cart = new Cart();
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

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Cart getCart() {
        return cart;
    }

    public double getTotal() {
        int total = 0;
        for (Map.Entry<Product, Integer> entry : cart.getCart().entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }

        return total;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", cart=" + cart +
                '}';
    }

    public boolean addToCart(Product product) {
        return this.cart.addToCart(product);
    }

    public boolean removeFromCart(Product product) {
        return this.cart.removeFromCart(product);
    }
}
