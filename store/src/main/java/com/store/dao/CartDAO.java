package com.store.dao;

import com.store.model.Cart;
import com.store.model.Customer;
import com.store.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CartDAO {

    private JdbcTemplate jdbcTemplate;
    private static final String driverClassName = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/db_store";
    private static final String dbUsername = "springuser";
    private static final String dbPassword = "ThePassword";

    public CartDAO() {
        this.jdbcTemplate = new JdbcTemplate(this.getDataSource());
    }
    public CartDAO(JdbcTemplate jdbcTemp) {
        this.jdbcTemplate = jdbcTemp;
    }

    //Works
    public Cart addItem(String username, int itemId){

        Cart cart = new Cart(username);
        String query = "Select cartID from carts where username ='" + cart.getUsername() + "'AND purchased = false";

        try {
            this.jdbcTemplate.queryForObject(
                    query, new RowMapper<Cart>() {
                        @Override
                        public Cart mapRow(ResultSet rs, int rowNumber) throws SQLException {
                            cart.setCartId(rs.getInt(1));
                            return cart;
                        }
                    });
        }
        //If cart does not exist create one
        catch(EmptyResultDataAccessException e){
            String insert = "INSERT INTO carts (username, purchased) VALUES (?,false)";
            jdbcTemplate.update(insert,username);
            //Get new cart id
            this.jdbcTemplate.queryForObject(
                    query, new RowMapper<Cart>() {
                        @Override
                        public Cart mapRow(ResultSet rs, int rowNumber) throws SQLException {
                            cart.setCartId(rs.getInt(1));
                            return cart;
                        }
                    });
        }

        //Add item to cart
        String insert = "insert into cart_items(cartId, itemID) values(?,?)";
        jdbcTemplate.update(insert, cart.getCartId(), itemId);
       return cart;
    }

    //Works
    public Collection<Product> getCartByUsername(String username){
        Collection<Product> products = new ArrayList<Product>();
        Cart cart = new Cart(username);
        ProductDAO productDAO = new ProductDAO(jdbcTemplate);
        String query = "Select cartID from carts where username ='" + username + "'AND purchased = false";

        try {
            this.jdbcTemplate.queryForObject(
                    query, new RowMapper<Cart>() {
                        @Override
                        public Cart mapRow(ResultSet rs, int rowNumber) throws SQLException {
                            cart.setCartId(rs.getInt(1));
                            return cart;
                        }
                    });
        }
        //No cart exists
        catch(EmptyResultDataAccessException e){
           return null;
        }

        return getCartById(cart.getCartId());
    }

    //Works
    public boolean removeItem(int itemId, int cartId){
        if(jdbcTemplate.update("Delete from cart_items where cartId = ? and itemId = ?", cartId, itemId) > 0){
            return true;
        }
        return false;
    }

    //Buy Item
    public boolean buyCart(int cartId){
        //Update Cart Status

        String update = "update carts set purchased= true where cartId=?";
        if(jdbcTemplate.update(update, cartId) > 0){
            //Update Products table
            Collection<Product> products = getCartById(cartId);
            for(Product p : products){
                jdbcTemplate.update("UPDATE products set stocked=false where itemId=?", p.getId());
            }
            return true;
        }
        else return false;
    }

    public Collection<Product> getCartById(int cartId){
        Collection<Product> products = new ArrayList<Product>();
        ProductDAO productDAO = new ProductDAO(jdbcTemplate);

        //Get item details
        this.jdbcTemplate.query(
                "SELECT itemID FROM cart_items where cartId =" + cartId, new Object[] { },
                (rs, rowNum) -> new Product(rs.getInt("itemId"))
        ).forEach(product -> products.add(productDAO.getProductByIdMin(product.getId())));

        return products;
    }

    //getItems Bought by customer
    public Collection<Product> itemsBoughtByUser(String username){
        Collection<Product> products = new ArrayList<Product>();
        Collection<Cart> pastPurchases = new ArrayList<Cart>();

        //Find Purchased Carts
        this.jdbcTemplate.query(
                "SELECT cartId FROM carts where username=\""+ username +"\"and purchased=true", new Object[] { },
                (rs, rowNum) -> new Cart(rs.getInt("cartId"))).forEach(cart -> pastPurchases.add(cart));

        for(Cart c : pastPurchases){
                ((ArrayList<Product>) products).addAll(products.size(),getCartById(c.getCartId()));
        }

        return products;
    }

    //list users who bought a specific product
    public Collection<String> usersWhoBoughtProduct(int id){
        Collection<Cart> carts = new ArrayList<Cart>();
        Collection<String> users = new ArrayList<String>();

        this.jdbcTemplate.query(
                "SELECT cartId FROM cart_items where itemId="+ id, new Object[] { },
                (rs, rowNum) -> new Cart(rs.getInt("cartId"))).forEach(cart -> carts.add(cart));

     for(Cart c : carts){
        this.jdbcTemplate.query(
                "SELECT username FROM carts where cartId="+ c.getCartId()+ " and purchased=true", new Object[] { },
                (rs, rowNum) -> new String(rs.getString("username"))).forEach(String -> users.add(String));
    }
    return users;
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
