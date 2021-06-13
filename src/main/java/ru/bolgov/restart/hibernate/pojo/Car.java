package ru.bolgov.restart.hibernate.pojo;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "CARS")
public class Car {

    @Id @GeneratedValue(generator = "increment")
    @Column(name = "ID")
    private int id;

    @Column(name = "BRAND")
    private String brand;

    @Column(name = "OWNER")
    private String owner;

    @Column(name = "PRODUCTION")
    private Date production;

    @Column(name = "MILEAGE")
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
