package com.example.demo.model;

import java.util.ArrayList;

public class ProductList {
    //This class is used to keep track of items that should be added to the displayed cart
    private ArrayList<Product> productList;

    //If freshly created account, create a new productlist
    public ProductList() {
        this.productList = new ArrayList<>();
    }

    //If already existing account, create productlist from already existing list in DB
    public ProductList(ArrayList<Product> list) {
        this.productList = list;
        System.out.println(printList(this.productList));
    }




    //Getters & Setters
    public ArrayList<Product> getProductList() {
        return productList;
    }

    public String printList(ArrayList<Product> list) {
        String tmp = "";
        for (Product product : list) {
            tmp += "Name: " + product.getName() + ", ID: " + product.getID() + ", Image: " + product.getImage() + ", Price: " + product.getPrice() + '\n';
        }
        return tmp;
    }
}
