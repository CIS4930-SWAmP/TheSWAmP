package com.store.model;
import java.util.UUID;

public class Login {
    private String sessionId;
    private String username;
    private int userId;
    private boolean isAdmin;

    public Login(String sessionId, String username, int userId, boolean isAdmin) {
        this.sessionId = sessionId;
        this.username = username;
        this.userId = userId;
    }

    public Login(String sessionId, String username, int userId) {
        this.sessionId = sessionId;
        this.username = username;
        this.userId = userId;
    }

    public Login() {
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return "Login{" +
                "sessionId=" + sessionId +
                ", username='" + username + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}


