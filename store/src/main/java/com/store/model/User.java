package com.store.model;

import java.util.Collection;

public class User {

    int cartId;
    int custId;
    boolean isPurchased;
    String username;
    Collection<Event> events;

    public User() {
        cartId = -1;
    }

    public User(int cartId, int custId, boolean isPurchased, String username) {
        this.cartId = cartId;
        this.custId = custId;
        this.isPurchased = isPurchased;
        this.username = username;
    }

    public User(int custId, boolean isPurchased, String username, Collection<Event> events) {
        this.custId = custId;
        this.isPurchased = isPurchased;
        this.username = username;
        this.events = events;
    }

    public User(String username) {

        this.username = username;
        isPurchased = false;
    }

    public User(int cartId) {
        this.cartId = cartId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public boolean getIsPurchased() {
        return isPurchased;
    }

    public void setIsPurchased(boolean isPurchased) {
        this.isPurchased = isPurchased;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<Event> getEvents() {
        return events;
    }

    public void setEvents(Collection<Event> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return "{" +
                "cartId=" + cartId +
                ", custId=" + custId +
                ", isPurchased=" + isPurchased +
                ", username='" + username + '\'' +
                '}';
    }
}
