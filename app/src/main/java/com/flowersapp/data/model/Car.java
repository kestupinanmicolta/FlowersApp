package com.flowersapp.data.model;

public class Car {
    private String name;
    private double price;
    private int cantidad;
    private int image;

    public Car(String name, double price, int cantidad, int image) {
        this.name = name;
        this.price = price;
        this.cantidad = cantidad;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
