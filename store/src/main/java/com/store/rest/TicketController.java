package com.store.rest;

import javax.servlet.http.HttpServlet;
import javax.servlet.*;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.stereotype.Controller;


import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

import com.store.model.*;

@Controller
@Path("customers")
public class TicketController extends HttpServlet{
    private TicketService eventService = new TicketService();

    public void init(ServletConfig config) {
        try{
            super.init(config);
            SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                    config.getServletContext());
        }catch(ServletException e){
        }
    }

    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Ticket getCustomer(@PathParam("username") String username){
        return eventService.getCustomer(username);
    }

    @POST
    @Path("")
    public Response createCustomer(
            @QueryParam("fname") String fname,
            @QueryParam("lname") String lname,
            @QueryParam("username") String username,
            @QueryParam("email") String email){
        if(eventService.createCustomer(fname, lname, username, email)) {
            return Response.status(200).build();
        }
        return Response.status(409).build();
    }

    @PUT
    @Path("")
    public Response updateCustomer(
            @QueryParam("fname") String fname,
            @QueryParam("lname") String lname,
            @QueryParam("username") String username,
            @QueryParam("email") String email){
        if(eventService.updateCustomer(fname, lname, username, email)){
            return Response.status(200).build();
        }
        return Response.status(400).build();
    }

    @DELETE
    @Path("{username}")
    public Response deleteCustomer(@PathParam("username") String username){
        if(eventService.deleteCustomer(username))
            return Response.status(200).build();
        return Response.status(400).build();
    }
}
