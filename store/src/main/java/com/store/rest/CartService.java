package com.store.rest;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.ArrayList;


import com.store.dao.*;
import com.store.model.*;

@Service
public class CartService {

    //@Autowired
    private CartDAO cartDAO = new CartDAO();
    private CustomerDAO customerDAO = new CustomerDAO();

    public int getCartId(String username){
        return cartDAO.getCartId(username);
    }

    public Collection<Product> getCart(String username){
        return cartDAO.getCartByUsername(username);
    }

    public Cart addItemToCart(int productId, String username){
        if(customerDAO.getCustomerByUsername(username) == null){
            return null;
        }
        return cartDAO.addItem(username, productId);
    }

    public boolean removeItem(int cartId, int itemId){
        return cartDAO.removeItem(itemId, cartId);
    }

    public boolean purchaseCart(int cartId){
        return cartDAO.buyCart(cartId);
    }

    public Collection<String > usersBoughtAProduct(int productId){
        return cartDAO.usersWhoBoughtProduct(productId);
    }

    public String shoppingCartToString(Collection<Product> products){
        String response = null;
        for(Product p : products){
            response = response + p.toStringShort();
        }
        return response;
    }
}
