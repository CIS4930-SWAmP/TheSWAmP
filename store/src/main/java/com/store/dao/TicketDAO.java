package com.store.dao;

import com.store.model.Ticket;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

@Component
public class TicketDAO {

    private JdbcTemplate jdbcTemplate;
    private static final String driverClassName = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/swamp_db";
    private static final String dbUsername = "springuser";
    private static final String dbPassword = "ThePassword";

    public TicketDAO() {
        this.jdbcTemplate = new JdbcTemplate(this.getDataSource());
    }

    //Create done
    public Boolean createTicket(Ticket ticket){
        String insert = "Insert into tickets(price, sellerId, availability, eventId)"
                + " VALUES (?, ?, ?, ?)";
        try{
            jdbcTemplate.update(insert, ticket.getPrice(), ticket.getSellerId(), ticket.getAvailability(), ticket.getEventId());
        }
        catch (DataIntegrityViolationException e){
            return false;
        }
        return true;
    }

    //Read done
    public Collection<Ticket> getEventTickets(int eventId){
        Collection<Ticket> tickets = new ArrayList<Ticket>();

        jdbcTemplate.query(
                "SELECT * FROM tickets where purchased = false and eventId =" + eventId, new Object[] { },
                (rs, rowNum) -> new Ticket(rs.getInt("ticketId"), rs.getInt("sellerId"), rs.getInt("eventId"),
                        rs.getBoolean("purchased"), rs.getDouble("price"), rs.getInt("buyerId"),
                        rs.getString("availability"))
        ).forEach(ticket -> tickets.add(ticket));

        return tickets;
    }

    //Done
    public Collection<Ticket> getSoldTickets(int userId){
        Collection<Ticket> tickets = new ArrayList<Ticket>();

        jdbcTemplate.query(
                "SELECT * FROM tickets where sellerId =" + userId, new Object[] { },
                (rs, rowNum) -> new Ticket(rs.getInt("ticketId"), rs.getInt("sellerId"), rs.getInt("eventId"),
                        rs.getBoolean("purchased"), rs.getDouble("price"), rs.getInt("buyerId"),
                        rs.getString("availability"))
        ).forEach(ticket -> tickets.add(ticket));

        return tickets;
    }


    public Collection<Ticket> getPurchasedTickets(int userId){
        Collection<Ticket> tickets = new ArrayList<Ticket>();

        jdbcTemplate.query(
                "SELECT * FROM tickets where buyerId =" + userId, new Object[] { },
                (rs, rowNum) -> new Ticket(rs.getInt("ticketId"), rs.getInt("sellerId"), rs.getInt("eventId"),
                        rs.getBoolean("purchased"), rs.getDouble("price"), rs.getInt("buyerId"),
                        rs.getString("availability"))
        ).forEach(ticket -> tickets.add(ticket));

        return tickets;
    }

    //Update Done
    public Boolean updateTicket(Ticket ticket){

        String sqlUpdate = "UPDATE tickets set price=?, availability=? where ticketId=?";
        return jdbcTemplate.update(sqlUpdate, ticket.getPrice(), ticket.getAvailability(), ticket.getId()) > 0;

    }

    //*
    public Boolean sellTicket(int ticketId, int buyerId){
        String query = "update tickets set purchased = ? and buyerId = ? where id = ?";
        return jdbcTemplate.update(query, true, buyerId, ticketId) > 0;
    }

    //Delete *
    public boolean deleteTicket(int ticketId){
        return jdbcTemplate.update("Delete from tickets where ticketId = ?", ticketId) > 0;
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
