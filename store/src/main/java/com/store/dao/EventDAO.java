package com.store.dao;

import com.store.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

@Component
public class EventDAO {

    private JdbcTemplate jdbcTemplate;
    private static final String driverClassName = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/swamp_db";
    private static final String dbUsername = "springuser";
    private static final String dbPassword = "ThePassword";

    public EventDAO() {
        this.jdbcTemplate = new JdbcTemplate(this.getDataSource());
    }

    @Autowired
    public EventDAO(JdbcTemplate jdbcTemp) {
        this.jdbcTemplate = jdbcTemp;
    }

    //Works
    public Collection<Event> getAllEvents(){

        Collection<Event> events = new ArrayList<Event>();
        this.jdbcTemplate.query(
                "SELECT * FROM events", new Object[] { },
                (rs, rowNum) -> new Event(rs.getInt("eventId"), rs.getString("title"),
                        rs.getString("eventDate"), rs.getString("description"))
        ).forEach(event -> events.add(event));

        return events;
    }

    //Works
    public Event getEventById(int eventId){
        Event event = new Event();
        String query ="SELECT * FROM events where eventId=" + eventId;

        try {
            return this.jdbcTemplate.queryForObject(
                    query, (rs, rowNumber) -> {
                        event.setEventId(rs.getInt(1));
                        event.setTitle(rs.getString(2));
                        event.setDate(rs.getString(3));
                        event.setDesc(rs.getString(4));
                        return event;
                    });
        }
        catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    public Collection<Event> getEventsByKeyword(String keyword){

        Collection<Event> events = new ArrayList<Event>();
        String search = "SELECT * FROM  events WHERE title like \'%" + keyword + "%\' or description like \'%" + keyword + "%\' " +
                "or eventDate like \'%" + keyword + "%\'";
        try {
            this.jdbcTemplate.query(search, new Object[]{},
                    (rs, rowNum) -> new Event(rs.getInt("eventId"), rs.getString("title"),
                            rs.getString("eventDate"), rs.getString("description"))
            ).forEach(event -> events.add(event));
        }
        catch(EmptyResultDataAccessException e){
            return null;
        }
        return events;
    }

    public boolean createEvent(Event event){
        String insert = "INSERT INTO events (title, eventDate, description)"
                + " VALUES (?, ?, ?)";
        try{
            jdbcTemplate.update(insert, event.getTitle(), event.getDate(), event.getDesc());
        }
        catch (DataIntegrityViolationException e){
            return false;
        }
        return true;
    }

    //Works
    public Boolean updateEvent(Event event){
        String sqlUpdate = "UPDATE events set title=?, eventDate=?, description=? where eventId=?";
        if(jdbcTemplate.update(sqlUpdate, event.getTitle(), event.getDate(), event.getDesc(), event.getEventId()) > 0)
            return true;
        return false;
    }


    public boolean deleteEvent(int eventId){
        if(jdbcTemplate.update("delete from events where eventId=?", eventId) > 0){
            return true;
        }
        else return false;
    }

    public DriverManagerDataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);
        return dataSource;

    }
}
