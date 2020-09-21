package com.example.demo.model;

import java.util.Objects;

public class Product {

    private String name;
    private String image;
    private int ID;
    private double price;
    private String description;
    private String catagory;

    public Product(String name, String image, int ID, double price, String description, String catagory) {
        this.name = name;
        this.image = image;
        this.ID = ID;
        this.price = price;
        this.description = description;
        this.catagory = catagory;
    }

    public Product(String name, String image, double price, String description, String catagory) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.description = description;
        this.catagory = catagory;
    }


    //Getters & Setters
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", ID=" + ID +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return ID == product.ID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }


}
