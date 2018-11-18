package com.store.dao;

import com.store.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
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
    private static final String url = "jdbc:mysql://localhost:3306/db_store";
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
    public Collection<Event> getAllProducts(){

        Collection<Event> events = new ArrayList<Event>();
        this.jdbcTemplate.query(
                "SELECT * FROM products", new Object[] { },
                (rs, rowNum) -> new Event(rs.getInt("itemId"), rs.getString("name"), rs.getDouble("msrp"),
                        rs.getDouble("salePrice"), rs.getInt("upc"), rs.getString("shortDescription"),
                        rs.getString("brandName"), rs.getString("size"), rs.getString("color"), rs.getString("gender"))
        ).forEach(product -> events.add(product));

        return events;
    }

    //Works
    public Event getProductById(int id){
        Event event = new Event(id);
        String query ="SELECT name, msrp, salePrice, upc, shortDescription, brandName, size, color, gender FROM products where itemId=" + id;

        try {
            return this.jdbcTemplate.queryForObject(
                    query, new RowMapper<Event>() {
                        @Override
                        public Event mapRow(ResultSet rs, int rowNumber) throws SQLException {
                            event.setName(rs.getString(1));
                            event.setMsrp(rs.getDouble(2));
                            event.setSalePrice(rs.getDouble(3));
                            event.setUpc(rs.getInt(4));
                            event.setDesc(rs.getString(5));
                            event.setBrand(rs.getString(6));
                            event.setSize(rs.getString(7));
                            event.setColor(rs.getString(8));
                            event.setGender(rs.getString(9));
                            return event;
                        }
                    });
        }
        catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    public Event getProductByIdMin(int id){
        Event event = new Event(id);
        String query ="SELECT name, msrp, salePrice FROM products where itemId=" + id;
        try {
            return this.jdbcTemplate.queryForObject(
                    query, new RowMapper<Event>() {
                        @Override
                        public Event mapRow(ResultSet rs, int rowNumber) throws SQLException {
                            event.setName(rs.getString(1));
                            event.setMsrp(rs.getDouble(2));
                            event.setSalePrice(rs.getDouble(3));
                            return event;
                        }
                    });
        }
        catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    public Collection<Event> getProductsByKeyword(String keyword){

        Collection<Event> events = new ArrayList<Event>();
        String search = "SELECT * FROM  products WHERE name like \'%" + keyword + "%\' or shortDescription like \'%" + keyword + "%\' " +
                "or brandName like \'%" + keyword + "%\' or size like \'%" + keyword + "%\' or color like \'%" + keyword + "%\' " +
                "or gender like \'%" + keyword + "%\'";
        try {
            this.jdbcTemplate.query(search, new Object[]{},
                    (rs, rowNum) -> new Event(rs.getInt("itemId"), rs.getString("name"), rs.getDouble("msrp"),
                            rs.getDouble("salePrice"), rs.getInt("upc"), rs.getString("shortDescription"),
                            rs.getString("brandName"), rs.getString("size"), rs.getString("color"), rs.getString("gender"))
            ).forEach(product -> events.add(product));
        }
        catch(EmptyResultDataAccessException e){
            return null;
        }
        return events;
    }

    public boolean removeProduct(int id){
        if(jdbcTemplate.update("delete from products where itemId=?", id) > 0){
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
