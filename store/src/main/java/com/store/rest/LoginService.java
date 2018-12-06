package com.store.rest;
import java.util.UUID;

import org.springframework.stereotype.Service;

import java.util.Collection;


import com.store.dao.*;
import com.store.model.*;

public class LoginService {
    private LoginDAO loginDAO = new LoginDAO();

    public Login createLogin(String sessionId, String username, int userId, boolean isAdmin) {
        Login login = new Login(sessionId, username, userId, isAdmin);
        return loginDAO.createLogin(login);
    }

    public Login readLogin(String sessionId) {
        return loginDAO.readLogin(sessionId);
    }

    public boolean deleteLogin(String sessionId) {
        return loginDAO.deleteLogin(sessionId);
    }

    public int isAdmin(String sessionId) { return loginDAO.isAdmin(sessionId); }
}
