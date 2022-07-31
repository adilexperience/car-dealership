package com.mobile.car_dealership.models;

import java.io.Serializable;

public class CarModel implements Serializable {
    int id;
    String name;
    String description;
    byte[] image;
    double hp;

    public CarModel() {
    }

    public CarModel(int id, String name, String description, byte[] image, double hp) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.hp = hp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }
}
