package com.store.rest;

import javax.servlet.http.HttpServlet;
import javax.servlet.*;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.stereotype.Controller;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

import com.store.model.*;

@Controller
@Path("items")
public class EventController extends HttpServlet  {


    //@Autowired
    private EventService eventService = new EventService();

    public void init(ServletConfig config) {
        try{
            super.init(config);
            SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                    config.getServletContext());
        }catch(ServletException e){
        }
    }


    //List all items
    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Event> getAllProducts() {
        Collection<Event> events = eventService.getAllProducts();
        return events;
    }

    //List items by keyword
    @GET
    @Path("/search/{keyword}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Event> getItemsByKeyword(@PathParam("keyword") String keyword){
        return eventService.getItemsByKeyword(keyword);
    }

    //List item by id
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Event getItemById(@PathParam("id") int id){
        return eventService.getProductById(id);
    }
}