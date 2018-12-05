package com.store.rest;

import org.springframework.stereotype.Service;

import java.util.Collection;


import com.store.dao.*;
import com.store.model.*;

@Service
public class UserService {

    //@Autowired
    private UserDAO userDAO = new UserDAO();

    public User createUser(String username, String password, String phone, String fname, String lname, String email, boolean isAdmin) {
        User user = new User(username, password, phone, fname, lname, email, isAdmin);
        return userDAO.createUser(user);
    }

    public User readUser(String username) {
        return userDAO.readUser(username);
    }

    public User updateUser(String username, String password, String phone, String fname, String lname, String email) {
        User user = new User(username, password, phone, fname, lname, email);
        return userDAO.updateUser(user);
    }

    public boolean deleteUser(String username) {
        return userDAO.deleteUser(username);
    }

    public User getUserById(int id){
        return userDAO.getUserById(id);
    }
}

