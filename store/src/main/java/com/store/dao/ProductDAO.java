package com.store.dao;

import com.store.model.Product;
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
public class ProductDAO {

    private JdbcTemplate jdbcTemplate;
    private static final String driverClassName = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/db_store";
    private static final String dbUsername = "springuser";
    private static final String dbPassword = "ThePassword";

    public ProductDAO() {
        this.jdbcTemplate = new JdbcTemplate(this.getDataSource());
    }

    @Autowired
    public ProductDAO(JdbcTemplate jdbcTemp) {
        this.jdbcTemplate = jdbcTemp;
    }

    //Works
    public Collection<Product> getAllProducts(){

        Collection<Product> products = new ArrayList<Product>();
        this.jdbcTemplate.query(
                "SELECT * FROM products", new Object[] { },
                (rs, rowNum) -> new Product(rs.getInt("itemId"), rs.getString("name"), rs.getDouble("msrp"),
                        rs.getDouble("salePrice"), rs.getInt("upc"), rs.getString("shortDescription"),
                        rs.getString("brandName"), rs.getString("size"), rs.getString("color"), rs.getString("gender"))
        ).forEach(product -> products.add(product));

        return products;
    }

    //Works
    public Product getProductById(int id){
        Product product = new Product(id);
        String query ="SELECT name, msrp, salePrice, upc, shortDescription, brandName, size, color, gender FROM products where itemId=" + id;

        try {
            return this.jdbcTemplate.queryForObject(
                    query, new RowMapper<Product>() {
                        @Override
                        public Product mapRow(ResultSet rs, int rowNumber) throws SQLException {
                            product.setName(rs.getString(1));
                            product.setMsrp(rs.getDouble(2));
                            product.setSalePrice(rs.getDouble(3));
                            product.setUpc(rs.getInt(4));
                            product.setDesc(rs.getString(5));
                            product.setBrand(rs.getString(6));
                            product.setSize(rs.getString(7));
                            product.setColor(rs.getString(8));
                            product.setGender(rs.getString(9));
                            return product;
                        }
                    });
        }
        catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    public Product getProductByIdMin(int id){
        Product product = new Product(id);
        String query ="SELECT name, msrp, salePrice FROM products where itemId=" + id;
        try {
            return this.jdbcTemplate.queryForObject(
                    query, new RowMapper<Product>() {
                        @Override
                        public Product mapRow(ResultSet rs, int rowNumber) throws SQLException {
                            product.setName(rs.getString(1));
                            product.setMsrp(rs.getDouble(2));
                            product.setSalePrice(rs.getDouble(3));
                            return product;
                        }
                    });
        }
        catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    public Collection<Product> getProductsByKeyword(String keyword){

        Collection<Product> products = new ArrayList<Product>();
        String search = "SELECT * FROM  products WHERE name like \'%" + keyword + "%\' or shortDescription like \'%" + keyword + "%\' " +
                "or brandName like \'%" + keyword + "%\' or size like \'%" + keyword + "%\' or color like \'%" + keyword + "%\' " +
                "or gender like \'%" + keyword + "%\'";
        try {
            this.jdbcTemplate.query(search, new Object[]{},
                    (rs, rowNum) -> new Product(rs.getInt("itemId"), rs.getString("name"), rs.getDouble("msrp"),
                            rs.getDouble("salePrice"), rs.getInt("upc"), rs.getString("shortDescription"),
                            rs.getString("brandName"), rs.getString("size"), rs.getString("color"), rs.getString("gender"))
            ).forEach(product -> products.add(product));
        }
        catch(EmptyResultDataAccessException e){
            return null;
        }
        return products;
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
