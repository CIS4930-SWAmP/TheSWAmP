package com.store.rest;

import javax.servlet.http.HttpServlet;
import javax.servlet.*;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

import com.store.model.*;

import java.util.Collection;

@Controller
@Path("tickets")
public class TicketController extends HttpServlet{
    private TicketService ticketService = new TicketService();

    public void init(ServletConfig config) {
        try{
            super.init(config);
            SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                    config.getServletContext());
        }catch(ServletException e){
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Ticket getCustomer(@PathParam("id") int id){
        return ticketService.getTicketById(id);
    }

    @GET
    @Path("/purchased/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Ticket> getPurchasedTickets(@PathParam("userId") int userId){
        return ticketService.getPurchasedTickets(userId);
    }

    @GET
    @Path("/sold/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Ticket> getSoldTickets(@PathParam("userId") int userId){
        return ticketService.getSoldTickets(userId);
    }

    @GET
    @Path("/event/{eventId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Ticket> getEventTickets(@PathParam("eventId") int eventId){
        return ticketService.getEventTickets(eventId);
    }

    @POST
    @Path("")
    public Response createTicket(
            @QueryParam("sellerId") int sellerId,
            @QueryParam("eventId") int eventId,
            @QueryParam("price") Double price,
            @QueryParam("avail") String avail,
            @QueryParam("quantity") int quantity) {
        if(ticketService.createTicket(sellerId,eventId,price,avail,quantity)){
            return Response.status(200).build();
        }
        return Response.status(409).build();
    }

    @PUT
    @Path("")
    public Response updateTicket(
            @QueryParam("price") Double price,
            @QueryParam("avail") String avail,
            @QueryParam("ticketId") int ticketId,
            @QueryParam("purch") boolean purch,
            @QueryParam("username") String username,
            @QueryParam("quantity") int quantity){
        if(ticketService.updateTicket(price, avail, ticketId, purch, username, quantity )){
            return Response.status(200).build();
        }
        return Response.status(400).build();
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteCustomer(@PathParam("id") int id){
        if(ticketService.deleteTicket(id))
            return Response.status(200).build();
        return Response.status(400).build();
    }
}
