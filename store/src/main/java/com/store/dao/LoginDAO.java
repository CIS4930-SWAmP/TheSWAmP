package com.store.dao;

import com.store.model.Login;
import java.util.UUID;
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


public class LoginDAO {
    private JdbcTemplate jdbcTemplate;
    private static final String driverClassName = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/swamp_db";
    private static final String dbUsername = "springuser";
    private static final String dbPassword = "ThePassword";

    public LoginDAO() {
        this.jdbcTemplate = new JdbcTemplate(this.getDataSource());
    }

    public Login createLogin(Login login) {
        String query = "INSERT INTO login (sessionId, username, userId) VALUES (?, ?, ?)";
        jdbcTemplate.update(
                query, login.getSessionId(), login.getUsername(), login.getUserId()
        );
        return login;
    }

    public Login readLogin(String sessionId) {
        String query = "SELECT * FROM login WHERE sessionId = ?";
        Login login = jdbcTemplate.queryForObject(
                query, new Object[]{sessionId}, new BeanPropertyRowMapper<>(Login.class)
        );
        return login;
    }

    public boolean deleteLogin(String sessionId) {
        boolean wasDeleted = false;
        String query = "DELETE FROM login WHERE sessionId = ?";
        int check = jdbcTemplate.update(
                query, sessionId
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
