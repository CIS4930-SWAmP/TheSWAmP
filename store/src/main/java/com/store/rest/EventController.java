package com.store.rest;

import javax.servlet.http.HttpServlet;
import javax.servlet.*;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.stereotype.Controller;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

import com.store.model.*;

@Controller
@Path("events")
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
    public Collection<Event> getAllEvents() {
        Collection<Event> events = eventService.getAllEvents();
        return events;
    }

    //List items by keyword
    @GET
    @Path("/search/{keyword}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Event> getEventsByKeyword(@PathParam("keyword") String keyword){
        return eventService.getEventsByKeyword(keyword);
    }

    //List item by id
    @GET
    @Path("/{eventId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Event getEventById(@PathParam("eventId") int eventId){
        return eventService.getEventById(eventId);
    }

    @POST
    public Response createEvent(
            @QueryParam("title") String title,
            @QueryParam("eventDate") String date,
            @QueryParam("description") String desc) {
        if(eventService.createEvent(title, date, desc)) {
            return Response.status(200).build();
        }
        return Response.status(409).build();
    }

    @PUT
    public Response updateEvent(
            @QueryParam("eventId") int eventId,
            @QueryParam("title") String title,
            @QueryParam("eventDate") String date,
            @QueryParam("description") String desc) {
        if(eventService.updateEvent(eventId, title, date, desc)) {
            return Response.status(200).build();
        }
        return Response.status(409).build();
    }

    @DELETE
    public Response deleteEvent(@QueryParam("eventId") int eventId){
        if(eventService.deleteEvent(eventId))
            return Response.status(200).build();
        return Response.status(400).build();
    }
}