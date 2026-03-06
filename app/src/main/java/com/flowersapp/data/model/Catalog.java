package com.flowersapp.data.model;

import java.io.Serializable;

public class Catalog implements Serializable {
    private int id;
    private int category;
    private String name;
    private double price;
    private int image;
    private String description;

    public Catalog(int id, int category, String name, double price, int image, String description) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.price = price;
        this.image = image;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
