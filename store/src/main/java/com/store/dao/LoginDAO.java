package com.store.dao;

import com.store.model.Login;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
    private JdbcTemplate jdbcTemplate;
    private static final String driverClassName = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://us-cdbr-iron-east-01.cleardb.net/heroku_d1de9c21ec8283e?reconnect=true";
    private static final String dbUsername = "bbc8595c09e838";
    private static final String dbPassword = "c5c39b6b";

    public LoginDAO() {
        this.jdbcTemplate = new JdbcTemplate(this.getDataSource());
    }

    public Login createLogin(Login login) {
        String query = "INSERT INTO login (sessionId, username, userId, isAdmin) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(
                query, login.getSessionId(), login.getUsername(), login.getUserId(), login.isAdmin()
        );
        return login;
    }

    public Login readLogin(String sessionId) {

        Login login = new Login();
        String toGet = "SELECT * FROM login WHERE sessionId = \"" + sessionId + "\"";

        try {
            return this.jdbcTemplate.queryForObject(
                    toGet, new RowMapper<Login>() {
                        @Override
                        public Login mapRow(ResultSet rs, int rowNumber) throws SQLException {
                            login.setSessionId(rs.getString(1));
                            login.setUsername(rs.getString(2));
                            login.setUserId(rs.getInt(3));
                            login.setAdmin(rs.getBoolean(4));
                            return login;
                        }
                    });
        }
        catch(EmptyResultDataAccessException e){
            return null;
        }
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

    public int isAdmin(String sessionId) {
        String query = "SELECT * FROM login WHERE sessionId = ?";
        Login login = null;
        try {
            login = jdbcTemplate.queryForObject(
                    query, new Object[]{sessionId}, new BeanPropertyRowMapper<>(Login.class)
            );
        }
        catch(EmptyResultDataAccessException e){
            return 40;
        }
        String username = login.getUsername();
        String checkAdmin = "SELECT isAdmin FROM users WHERE username = ?";
        try {
            int toReturn = jdbcTemplate.queryForObject(
                    checkAdmin, new Object[] {username}, Integer.class
            );
            return toReturn;
        }
        catch(EmptyResultDataAccessException e){
            return 30;
        }
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
