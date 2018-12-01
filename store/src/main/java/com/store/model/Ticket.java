package com.store.model;

public class Ticket {
    private int id;
    private int buyerId;
    private int sellerId;
    private int eventId;
    private double price;
    private String availability;
    private boolean purchased;

    public Ticket() {
    }

    public Ticket(int id, double price, String availability) {
        this.id = id;
        this.price = price;
        this.availability = availability;
    }

    public Ticket(int id){
        this.id = id;
    }

    public Ticket(int sellerId, int eventId, double price, String availability) {
        this.sellerId = sellerId;
        this.eventId = eventId;
        this.price = price;
        this.availability = availability;
    }

    public Ticket(int id, int sellerId, int eventId, boolean purchased, double price,int buyerId, String availability) {
        this.id = id;
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.eventId = eventId;
        this.price = price;
        this.availability = availability;
        this.purchased = purchased;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public boolean getPurchased() {
        return purchased;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }
}

