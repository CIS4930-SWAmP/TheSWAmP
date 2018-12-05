package com.store.dao;

import com.store.model.User;
import com.store.model.Event;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
    private static final String url = "jdbc:mysql://localhost:3306/swamp_db";
    private static final String dbUsername = "springuser";
    private static final String dbPassword = "ThePassword";

    public UserDAO() {
        this.jdbcTemplate = new JdbcTemplate(this.getDataSource());
    }
    public UserDAO(JdbcTemplate jdbcTemp) {
        this.jdbcTemplate = jdbcTemp;
    }

    //Create new user
    public User createUser(User user) {
        String query = "INSERT INTO users (username, password, phone, fname, lname, email, isAdmin) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(
                query, user.getUsername(), user.getPassword(), user.getPhone(), user.getFname(), user.getLname(), user.getEmail(), user.getAdmin()
        );
        return user;
    }

    //Get user by username
    public User readUser(String username) {
//        try {
//            String toGet = "SELECT * FROM users WHERE username = ?";
//            User user = jdbcTemplate.queryForObject(
//                    toGet, new Object[]{username}, new BeanPropertyRowMapper<>(User.class)
//            );
//            return user;
//        }
//        catch(EmptyResultDataAccessException e){
//            return null;
//        }

        User user = new User();
        String toGet = "SELECT * FROM users WHERE username = \"" + username + "\"";

        try {
            return this.jdbcTemplate.queryForObject(
                    toGet, new RowMapper<User>() {
                        @Override
                        public User mapRow(ResultSet rs, int rowNumber) throws SQLException {
                            user.setId(rs.getInt(1));
                            user.setUsername(rs.getString(2));
                            user.setPassword(rs.getString(3));
                            user.setPhone(rs.getString(4));
                            user.setLname(rs.getString(5));
                            user.setFname(rs.getString(6));
                            user.setEmail(rs.getString(7));
                            user.setAdmin(rs.getBoolean(8));
                            return user;
                        }
                    });
        }
        catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    //Update user
    public User updateUser(User user) {
        String query = "UPDATE users SET password = ?, phone = ?, fname = ?, lname = ?, email = ? WHERE username = ?";
        jdbcTemplate.update(
                query, user.getPassword(), user.getPhone(), user.getFname(), user.getLname(), user.getEmail(), user.getUsername()
        );
        return user;
    }

    //Delete by username
    public boolean deleteUser(String username) {
        boolean wasDeleted = false;
        String query = "DELETE FROM users WHERE username = ?";
        int check = jdbcTemplate.update(
                query, username
        );
        if(check != 0) {
            wasDeleted = true;
        }
        return wasDeleted;
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
