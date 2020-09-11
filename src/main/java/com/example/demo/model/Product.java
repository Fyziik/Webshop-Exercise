package com.example.demo.model;

public class Product {

    private String name;
    private String image;
    private int ID;
    private double price;

    public Product(String name, String image, int ID, double price) {
        this.name = name;
        this.image = image;
        this.ID = ID;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
