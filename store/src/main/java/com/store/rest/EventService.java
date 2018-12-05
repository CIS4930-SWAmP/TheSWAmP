package com.store.rest;

import org.springframework.stereotype.Service;

import java.util.Collection;

import com.store.dao.*;
import com.store.model.*;

@Service
public class EventService {

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
        Event oldEvent = getEventById(eventId);
        Event event = new Event();
        event.setEventId(eventId);
        if(title != null) {
            event.setTitle(title);
        }
        else {
            event.setTitle(oldEvent.getTitle());
        }
        if(desc != null) {
            event.setDesc(desc);
        }
        else {
            event.setDesc(oldEvent.getDesc());
        }
        if(date != null) {
            event.setDate(date);
        }
        else {
            event.setDate(oldEvent.getDate());
        }
        return eventDAO.updateEvent(event);
    }

    public boolean deleteEvent(int eventId){
        return eventDAO.deleteEvent(eventId);
    }
}
