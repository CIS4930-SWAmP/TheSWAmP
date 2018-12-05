package com.store.dao;

import com.store.model.Ticket;
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
        String insert = "Insert into tickets(price, sellerId, availability, eventId, quantity) VALUES (?, ?, ?, ?, ?)";
        try{
            jdbcTemplate.update(insert, ticket.getPrice(), ticket.getSellerId(), ticket.getAvailability(), ticket.getEventId(), ticket.getQuantity());
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
                        rs.getString("availability"),rs.getInt("quantity"))
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
                        rs.getString("availability"),rs.getInt("quantity"))
        ).forEach(ticket -> tickets.add(ticket));

        return tickets;
    }


    public Collection<Ticket> getPurchasedTickets(int userId){
        Collection<Ticket> tickets = new ArrayList<Ticket>();

        jdbcTemplate.query(
                "SELECT * FROM tickets where buyerId =" + userId, new Object[] { },
                (rs, rowNum) -> new Ticket(rs.getInt("ticketId"), rs.getInt("sellerId"), rs.getInt("eventId"),
                        rs.getBoolean("purchased"), rs.getDouble("price"), rs.getInt("buyerId"),
                        rs.getString("availability"), rs.getInt("quantity"))
        ).forEach(ticket -> tickets.add(ticket));

        return tickets;
    }

    public Ticket getTicketById(int id){
        Ticket ticket = new Ticket();
        String query ="SELECT * FROM tickets where ticketId=" + id;

        try {
            return this.jdbcTemplate.queryForObject(
                    query, new RowMapper<Ticket>() {
                        @Override
                        public Ticket mapRow(ResultSet rs, int rowNumber) throws SQLException {
                            ticket.setId(rs.getInt(1));
                            ticket.setSellerId(rs.getInt(2));
                            ticket.setEventId(rs.getInt(3));
                            ticket.setPurchased(rs.getBoolean(4));
                            ticket.setPrice(rs.getDouble(5));
                            ticket.setBuyerId(rs.getInt(6));
                            ticket.setAvailability(rs.getString(7));
                            ticket.setQuantity(rs.getInt(8));
                            return ticket;
                        }
                    });
        }
        catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    //Update Done
    public Boolean updateTicket(Ticket ticket){

        String sqlUpdate = "UPDATE tickets set price=?, availability=?, purchased=?, buyerId = ?, quantity = ? where ticketId=?";
        return jdbcTemplate.update(sqlUpdate, ticket.getPrice(), ticket.getAvailability(), ticket.getPurchased(), ticket.getBuyerId(), ticket.getQuantity(), ticket.getId()) > 0;
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
