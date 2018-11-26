package com.store.rest;

import org.springframework.stereotype.Service;

import java.util.Collection;

import com.store.dao.*;
import com.store.model.*;
import java.util.Date;

@Service
public class EventService {


    //@Autowired
    private EventDAO eventDAO = new EventDAO();

    public Collection<Event> getAllEvents() {

        Collection<Event> events = eventDAO.getAllEvents();
        return events;
    }

    public Collection<Event> getEventsByKeyword(String keyword){
        return eventDAO.getEventsByKeyword(keyword);
    }

    public Event getEventById(int id){
        return eventDAO.getEventById(id);
    }

    public boolean createEvent(String title, String date, String desc){
        Event event = new Event(title, date, desc);
        return eventDAO.createEvent(event);
    }

    public boolean updateEvent(int eventId, String title, String date, String desc) {
        Event event = new Event(eventId, title, date, desc);
        return eventDAO.updateEvent(event);
    }

    public boolean deleteEvent(int eventId){
        return eventDAO.deleteEvent(eventId);
    }
}
