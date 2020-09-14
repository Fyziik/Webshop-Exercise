package com.example.demo.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Cart {
    //This class is used to keep track of items that should be added to the displayed cart
    private HashMap<Product, Integer> cart;

    //If freshly created account, create a new cart
    public Cart() {
        this.cart = new HashMap<>();
    }

    //If already existing account, create cart from already existing list in DB
    public Cart(ArrayList<Product> list) {
        this.cart = mapToHashmap(list);
    }

    private HashMap<Product, Integer> mapToHashmap(ArrayList<Product> list) {
        HashMap<Product, Integer> tmp = new HashMap<>();

        for (Product product : list) {

            //If product isnt in cart, add it to the cart
            if (!tmp.containsKey(product)) {
                tmp.put(product, 1);
            }
            //Else find product, and increase amount
            else {
                int amount = tmp.get(product) + 1;
                tmp.replace(product, amount);
            }

        }

        return tmp;
    }

    public boolean addToCart(Product product) {
        if (!cart.containsKey(product)) {
            cart.put(product, 1);
        }
        //Else find product, and increase amount
        else {
            int amount = cart.get(product) + 1;
            cart.replace(product, amount);
        }
        return true;
    }


    //Getters & Setters
    public HashMap<Product, Integer> getCart() {
        return cart;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cart=" + cart +
                '}';
    }
}
