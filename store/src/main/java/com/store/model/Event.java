package com.store.model;

public class Event {
    private int eventId;
    private String eventDate;
    private String description;
    private String title;

    public Event(int eventId, String title, String date, String desc) {
        this.eventId = eventId;
        this.eventDate = date;
        this.description = desc;
        this.title = title;
    }

    public Event(String title, String date, String desc) {
        this.eventDate = date;
        this.description = desc;
        this.title = title;
    }

    public Event() {
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getDate() {
        return eventDate;
    }

    public void setDate(String date) {
        this.eventDate = date;
    }

    public String getDesc() {
        return description;
    }

    public void setDesc(String desc) {
        this.description = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public java.lang.String toString() {
        return "{" +
                "eventId=" + eventId +
                ", title='" + title + '\'' +
                ", date=" + eventDate +
                ", description='" + description + '\'' +
                '}';
    }
}