package com.store.dao;

import com.store.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CustomerDAO {

    private JdbcTemplate jdbcTemplate;
    private static final String driverClassName = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/db_store";
    private static final String dbUsername = "springuser";
    private static final String dbPassword = "ThePassword";

    public CustomerDAO() {
        this.jdbcTemplate = new JdbcTemplate(this.getDataSource());
    }

    public CustomerDAO(JdbcTemplate jdbcTemp) {
        this.jdbcTemplate = jdbcTemp;
    }

    //Works
    public Boolean createCustomer(Customer customer){
        String insert = "INSERT INTO customers (fname, lname, username, email)"
                + " VALUES (?, ?, ?, ?)";
        if(jdbcTemplate.update(insert, customer.getFName(), customer.getLName(), customer.getUsername(), customer.getEmail()) > 0)
        return true;

        return false;
    }

    //Works
    public Customer getCustomerByUsername(String username){
        Customer customer = new Customer(username);

        //Check if user exits
        String query ="SELECT id, fname, lname, email FROM customers where username=\"" + username+"\"";
        try {
            return this.jdbcTemplate.queryForObject(
                    query, new RowMapper<Customer>() {
                        @Override
                        public Customer mapRow(ResultSet rs, int rowNumber) throws SQLException {
                            customer.setId(rs.getInt(1));
                            customer.setFName(rs.getString(2));
                            customer.setLName(rs.getString(3));
                            customer.setEmail(rs.getString(4));
                            return customer;
                        }
                    });
        }
        catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    //Works
    public Boolean updateCustomer(Customer customer){
        String sqlUpdate = "UPDATE customers set fname=?, lname=?, username=?, email=? where id=?";
        if(jdbcTemplate.update(sqlUpdate, customer.getFName(), customer.getLName(), customer.getUsername(), customer.getEmail(), customer.getId()) > 0)
            return true;
        return false;
    }

    //Works
    public boolean deleteCustomer(String username){
        if(jdbcTemplate.update("Delete from customers where username = ?", username) > 0){
            return true;
        }
        return false;
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
