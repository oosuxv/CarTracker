package com.company.model;


import javax.persistence.*;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long carId;

    @Column
    private int year;

    @Column
    private String maker;

    @Column
    private String model;

    @Column
    private String bodyStyle;

    public Car() {
    }

    public Car(int year, String maker, String model, String bodyStyle) {
        this.year = year;
        this.maker = maker;
        this.model = model;
        this.bodyStyle = bodyStyle;
    }

    public long getCarId() {
        return carId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBodyStyle() {
        return bodyStyle;
    }

    public void setBodyStyle(String bodyStyle) {
        this.bodyStyle = bodyStyle;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", year=" + year +
                ", maker='" + maker + '\'' +
                ", model='" + model + '\'' +
                ", bodyStyle='" + bodyStyle + '\'' +
                '}';
    }
}
