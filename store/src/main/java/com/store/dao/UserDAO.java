package com.store.dao;

import com.store.model.User;
import com.store.model.Event;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

@Component
public class UserDAO {

    private JdbcTemplate jdbcTemplate;
    private static final String driverClassName = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/db_store";
    private static final String dbUsername = "springuser";
    private static final String dbPassword = "ThePassword";

    public UserDAO() {
        this.jdbcTemplate = new JdbcTemplate(this.getDataSource());
    }
    public UserDAO(JdbcTemplate jdbcTemp) {
        this.jdbcTemplate = jdbcTemp;
    }

    //Works
    public User addItem(String username, int itemId){

        User user = new User(username);
        String query = "Select cartID from carts where username ='" + user.getUsername() + "'AND purchased = false";

        try {
            this.jdbcTemplate.queryForObject(
                    query, new RowMapper<User>() {
                        @Override
                        public User mapRow(ResultSet rs, int rowNumber) throws SQLException {
                            user.setCartId(rs.getInt(1));
                            return user;
                        }
                    });
        }
        //If user does not exist create one
        catch(EmptyResultDataAccessException e){
            String insert = "INSERT INTO carts (username, purchased) VALUES (?,false)";
            jdbcTemplate.update(insert,username);
            //Get new user id
            this.jdbcTemplate.queryForObject(
                    query, new RowMapper<User>() {
                        @Override
                        public User mapRow(ResultSet rs, int rowNumber) throws SQLException {
                            user.setCartId(rs.getInt(1));
                            return user;
                        }
                    });
        }

        //Add item to user
        String insert = "insert into cart_items(cartId, itemID) values(?,?)";
        jdbcTemplate.update(insert, user.getCartId(), itemId);
       return user;
    }

    //Works
    public Collection<Event> getCartByUsername(String username){
        Collection<Event> events = new ArrayList<Event>();
        User user = new User(username);
        EventDAO eventDAO = new EventDAO(jdbcTemplate);
        String query = "Select cartID from carts where username ='" + username + "'AND purchased = false";

        try {
            this.jdbcTemplate.queryForObject(
                    query, new RowMapper<User>() {
                        @Override
                        public User mapRow(ResultSet rs, int rowNumber) throws SQLException {
                            user.setCartId(rs.getInt(1));
                            return user;
                        }
                    });
        }
        //No user exists
        catch(EmptyResultDataAccessException e){
           return null;
        }

        return getCartById(user.getCartId());
    }

    //Works
    public boolean removeItem(int itemId, int cartId){
        User user = new User();
        String query = "Select cartId from carts where cartId =" + cartId + " AND purchased = false";
        try {
            this.jdbcTemplate.queryForObject(
                    query, new RowMapper<User>() {
                        @Override
                        public User mapRow(ResultSet rs, int rowNumber) throws SQLException {
                            user.setCartId(rs.getInt(1));
                            return user;
                        }
                    });
        }
        //No user exists with that item
        catch(EmptyResultDataAccessException e){
            return false;
        }

        if(jdbcTemplate.update("Delete from cart_items where cartId = ? and itemId = ?", cartId, itemId) > 0){
            return true;
        }
        return false;
    }

    //Buy Item
    public boolean buyCart(int cartId){
        //Update User Status

        String update = "update carts set purchased= true where cartId=?";
        if(jdbcTemplate.update(update, cartId) > 0){
            //Update Products table
            Collection<Event> events = getCartById(cartId);
            for(Event p : events){
                jdbcTemplate.update("UPDATE products set stocked=false where itemId=?", p.getId());
            }
            return true;
        }
        else return false;
    }

    public Collection<Event> getCartById(int cartId){
        Collection<Event> events = new ArrayList<Event>();
        EventDAO eventDAO = new EventDAO(jdbcTemplate);

        //Get item details
        this.jdbcTemplate.query(
                "SELECT itemID FROM cart_items where cartId =" + cartId, new Object[] { },
                (rs, rowNum) -> new Event(rs.getInt("itemId"))
        ).forEach(product -> events.add(eventDAO.getProductByIdMin(product.getId())));

        return events;
    }

    //getItems Bought by customer
    public Collection<Event> itemsBoughtByUser(String username){
        Collection<Event> events = new ArrayList<Event>();
        Collection<User> pastPurchases = new ArrayList<User>();

        //Find Purchased Carts
        this.jdbcTemplate.query(
                "SELECT cartId FROM carts where username=\""+ username +"\"and purchased=true", new Object[] { },
                (rs, rowNum) -> new User(rs.getInt("cartId"))).forEach(user -> pastPurchases.add(user));

        for(User c : pastPurchases){
                ((ArrayList<Event>) events).addAll(events.size(),getCartById(c.getCartId()));
        }

        return events;
    }

    //list users who bought a specific product
    public Collection<String> usersWhoBoughtProduct(int id){
        Collection<User> carts = new ArrayList<User>();
        Collection<String> users = new ArrayList<String>();

        this.jdbcTemplate.query(
                "SELECT cartId FROM cart_items where itemId="+ id, new Object[] { },
                (rs, rowNum) -> new User(rs.getInt("cartId"))).forEach(user -> carts.add(user));

     for(User c : carts){
        this.jdbcTemplate.query(
                "SELECT username FROM carts where cartId="+ c.getCartId()+ " and purchased=true", new Object[] { },
                (rs, rowNum) -> new String(rs.getString("username"))).forEach(String -> users.add(String));
    }
    return users;
    }

    public int getCartId(String username){
        User user = new User(username);
        String query = "Select cartID from carts where username ='" + user.getUsername() + "'AND purchased = false";

        try {
            this.jdbcTemplate.queryForObject(
                    query, new RowMapper<User>() {
                        @Override
                        public User mapRow(ResultSet rs, int rowNumber) throws SQLException {
                            user.setCartId(rs.getInt(1));
                            return user;
                        }
                    });
        }
        catch(EmptyResultDataAccessException e){
            return 0;
        }
        return user.getCartId();
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
