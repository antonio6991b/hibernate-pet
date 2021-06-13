package ru.bolgov.restart.hibernate.pojo;

import java.sql.Date;

public class Car {
    private int id;
    private String brand;
    private String owner;
    private Date production;
    private int mileage;

    public Car(){}

    public Car(String brand, String owner, Date production, int mileage) {
        this.brand = brand;
        this.owner = owner;
        this.production = production;
        this.mileage = mileage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Date getProduction() {
        return production;
    }

    public void setProduction(Date production) {
        this.production = production;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", owner='" + owner + '\'' +
                ", production=" + production +
                ", mileage=" + mileage +
                '}';
    }
}
