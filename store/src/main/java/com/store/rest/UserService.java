package com.store.rest;

import org.springframework.stereotype.Service;

import java.util.Collection;


import com.store.dao.*;
import com.store.model.*;

@Service
public class UserService {

    //@Autowired
    private UserDAO userDAO = new UserDAO();
    private TicketDAO ticketDAO = new TicketDAO();

    public int getCartId(String username){
        return userDAO.getCartId(username);
    }

    public Collection<Event> getCart(String username){
        return userDAO.getCartByUsername(username);
    }

    public User addItemToCart(int productId, String username){
        if(ticketDAO.getCustomerByUsername(username) == null){
            return null;
        }
        return userDAO.addItem(username, productId);
    }

    public boolean removeItem(int cartId, int itemId){
        return userDAO.removeItem(itemId, cartId);
    }

    public boolean purchaseCart(int cartId){
        return userDAO.buyCart(cartId);
    }

    public Collection<String > usersBoughtAProduct(int productId){
        return userDAO.usersWhoBoughtProduct(productId);
    }

    public String shoppingCartToString(Collection<Event> events){
        String response = null;
        for(Event p : events){
            response = response + p.toStringShort();
        }
        return response;
    }
}
