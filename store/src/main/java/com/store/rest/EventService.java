package com.store.rest;

import org.springframework.stereotype.Service;

import java.util.Collection;

import com.store.dao.*;
import com.store.model.*;

@Service
public class EventService {


    //@Autowired
    private EventDAO eventDAO = new EventDAO();


    public String getMsg( String msg) {
        return "Hello : " + msg;
    }

    public Collection<Event> getAllProducts() {

        Collection<Event> events = eventDAO.getAllProducts();
        return events;
    }

    public Collection<Event> getItemsByKeyword(String keyword){
        return eventDAO.getProductsByKeyword(keyword);
    }

    public Event getProductById(int id){
        return eventDAO.getProductById(id);
    }
}
