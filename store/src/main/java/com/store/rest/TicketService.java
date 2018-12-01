package com.store.rest;

import org.springframework.stereotype.Service;


import com.store.dao.*;
import com.store.model.*;

import java.util.Collection;

@Service
public class TicketService {


    //@Autowired
    private TicketDAO ticketDAO = new TicketDAO();

    public boolean createTicket(int sellerId, int eventId, Double price, String availability){
        Ticket ticket = new Ticket(sellerId, eventId, price, availability);
        return ticketDAO.createTicket(ticket);
    }

    public boolean updateTicket(Double price, String availability, int ticketId, boolean purchased, String username){
        Ticket ticket = new Ticket(ticketId);
        Ticket oldTicket = ticketDAO.getTicketById(ticketId);
        UserDAO userDAO = new UserDAO();

        if(username != null) {
            int buyerId = userDAO.readUser(username).getId();
            ticket.setBuyerId(buyerId);
        }

        ticket.setPurchased(purchased);

        if(price != null)
            ticket.setPrice(price);
        else
            ticket.setPrice(oldTicket.getPrice());

        if(availability != null)
            ticket.setAvailability(availability);
        else
            ticket.setAvailability(oldTicket.getAvailability());


        return ticketDAO.updateTicket(ticket);

    }

    public boolean deleteTicket(int ticketId){
        return ticketDAO.deleteTicket(ticketId);
    }

    public Ticket getTicketById(int ticketId){
        return ticketDAO.getTicketById(ticketId);
    }

    public Collection<Ticket> getSoldTickets(int userId){
        return ticketDAO.getSoldTickets(userId);
    }

    public Collection<Ticket> getPurchasedTickets(int userId){
        return ticketDAO.getPurchasedTickets(userId);
    }

    public Collection<Ticket> getEventTickets(int eventId){
        return ticketDAO.getEventTickets(eventId);
    }
}
