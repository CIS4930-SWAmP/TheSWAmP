package com.store.model;

import java.util.Collection;

public class Event {
    private int id;
    private String name;
    private double msrp;
    private double salePrice;
    private int upc;
    private String desc;
    private String brand;
    private String size;
    private String color;
    private String gender;

    public Event(int id, String name, double msrp, double salePrice, int upc, String desc, String brand, String size, String color, String gender) {
        this.id = id;
        this.name = name;
        this.msrp = msrp;
        this.salePrice = salePrice;
        this.upc = upc;
        this.desc = desc;
        this.brand = brand;
        this.size = size;
        this.color = color;
        this.gender = gender;
    }

    public Event() {
    }

    public Event(int id) {
        this.id = id;
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

    public double getMsrp() {
        return msrp;
    }

    public void setMsrp(double msrp) {
        this.msrp = msrp;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public int getUpc() {
        return upc;
    }

    public void setUpc(int upc) {
        this.upc = upc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public java.lang.String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", msrp=" + msrp +
                ", salePrice=" + salePrice +
                ", upc=" + upc +
                ", desc='" + desc + '\'' +
                ", brand='" + brand + '\'' +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    public String toStringShort(){
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", msrp=" + msrp +
                ", salePrice=" + salePrice +
                '}';
    }
}
