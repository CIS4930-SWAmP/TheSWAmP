package com.store.rest;

import org.springframework.stereotype.Service;


import com.store.dao.*;
import com.store.model.*;

@Service
public class TicketService {


    //@Autowired
    private TicketDAO ticketDAO = new TicketDAO();

    public Ticket getCustomer(String username){
        return ticketDAO.getCustomerByUsername(username);
    }

    public boolean createCustomer(String fname, String lname, String username, String email){
        Ticket ticket = new Ticket(fname, lname, username, email);
        return ticketDAO.createCustomer(ticket);
    }

    public boolean updateCustomer(String fname, String lname, String username, String email){
        Ticket ticket = ticketDAO.getCustomerByUsername(username);
        if (ticket != null) {
            ticket.setFName(fname);
            ticket.setLName(lname);
            ticket.setEmail(email);
            return ticketDAO.updateCustomer(ticket);
        }
        return false;
    }

    public boolean deleteCustomer(String username){
        return ticketDAO.deleteCustomer(username);
    }
}
