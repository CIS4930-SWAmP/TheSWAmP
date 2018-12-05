package com.store.model;

public class User {
    private int id;
    private String username;
    private String password;
    private String phone;
    private String fname;
    private String lname;
    private String email;
    private boolean isAdmin;

    public User(String username, String password, String phone, String fname, String lname, String email, boolean isAdmin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    public User(int id, String username, String password, String phone, String fname, String lname, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
    }

    public User(String username, String password, String phone, String fname, String lname, String email) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", email='" + email + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
