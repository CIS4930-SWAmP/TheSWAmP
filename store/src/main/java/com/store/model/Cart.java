package com.store.model;

import java.util.Collection;

public class Cart {

    int cartId;
    int custId;
    boolean isPurchased;
    String username;
    Collection<Product> products;

    public Cart(int cartId, int custId, boolean isPurchased, String username) {
        this.cartId = cartId;
        this.custId = custId;
        this.isPurchased = isPurchased;
        this.username = username;
    }

    public Cart(int custId, boolean isPurchased, String username, Collection<Product> products) {
        this.custId = custId;
        this.isPurchased = isPurchased;
        this.username = username;
        this.products = products;
    }

    public Cart(String username) {

        this.username = username;
        isPurchased = false;
    }

    public Cart(int cartId) {
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

    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", custId=" + custId +
                ", isPurchased=" + isPurchased +
                ", username='" + username + '\'' +
                '}';
    }
}
