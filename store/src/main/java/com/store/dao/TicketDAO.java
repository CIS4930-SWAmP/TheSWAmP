//package com.store.dao;
//
//import com.store.model.Ticket;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.stereotype.Component;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//@Component
//public class TicketDAO {
//
//    private JdbcTemplate jdbcTemplate;
//    private static final String driverClassName = "com.mysql.jdbc.Driver";
//    private static final String url = "jdbc:mysql://localhost:3306/db_store";
//    private static final String dbUsername = "springuser";
//    private static final String dbPassword = "ThePassword";
//
//    public TicketDAO() {
//        this.jdbcTemplate = new JdbcTemplate(this.getDataSource());
//    }
//
//    public TicketDAO(JdbcTemplate jdbcTemp) {
//        this.jdbcTemplate = jdbcTemp;
//    }
//
//    //Works
//    public Boolean createCustomer(Ticket ticket){
//        String insert = "INSERT INTO customers (fname, lname, username, email)"
//                + " VALUES (?, ?, ?, ?)";
//        try{
//            jdbcTemplate.update(insert, ticket.getFName(), ticket.getLName(), ticket.getUsername(), ticket.getEmail());
//        }
//        catch (DataIntegrityViolationException e){
//            return false;
//        }
//        return true;
//    }
//
//    //Works
//    public Ticket getCustomerByUsername(String username){
//        Ticket ticket = new Ticket(username);
//
//        //Check if user exits
//        String query ="SELECT id, fname, lname, email FROM customers where username=\"" + username+"\"";
//        try {
//            return this.jdbcTemplate.queryForObject(
//                    query, new RowMapper<Ticket>() {
//                        @Override
//                        public Ticket mapRow(ResultSet rs, int rowNumber) throws SQLException {
//                            ticket.setId(rs.getInt(1));
//                            ticket.setFName(rs.getString(2));
//                            ticket.setLName(rs.getString(3));
//                            ticket.setEmail(rs.getString(4));
//                            return ticket;
//                        }
//                    });
//        }
//        catch(EmptyResultDataAccessException e){
//            return null;
//        }
//    }
//
//    //Works
//    public Boolean updateCustomer(Ticket ticket){
//        String sqlUpdate = "UPDATE customers set fname=?, lname=?, username=?, email=? where id=?";
//        if(jdbcTemplate.update(sqlUpdate, ticket.getFName(), ticket.getLName(), ticket.getUsername(), ticket.getEmail(), ticket.getId()) > 0)
//            return true;
//        return false;
//    }
//
//    //Works
//    public boolean deleteCustomer(String username){
//        if(jdbcTemplate.update("Delete from customers where username = ?", username) > 0){
//            return true;
//        }
//        return false;
//    }
//
//    public DriverManagerDataSource getDataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(driverClassName);
//        dataSource.setUrl(url);
//        dataSource.setUsername(dbUsername);
//        dataSource.setPassword(dbPassword);
//        return dataSource;
//
//    }
//}
