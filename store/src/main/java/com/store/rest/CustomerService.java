package com.store.rest;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.ArrayList;


import com.store.dao.*;
import com.store.model.*;

@Service
public class CustomerService {


    //@Autowired
    private CustomerDAO customerDAO = new CustomerDAO();

    public Customer getCustomer(String username){
        return customerDAO.getCustomerByUsername(username);
    }

    public boolean createCustomer(String fname, String lname, String username, String email){
        Customer customer = new Customer(fname, lname, username, email);
        return customerDAO.createCustomer(customer);
    }

    public boolean updateCustomer(String fname, String lname, String username, String email){
        Customer customer = customerDAO.getCustomerByUsername(username);
        if (customer!= null) {
            customer.setFName(fname);
            customer.setLName(lname);
            customer.setEmail(email);
            return customerDAO.updateCustomer(customer);
        }
        return false;
    }

    public boolean deleteCustomer(String username){
        return customerDAO.deleteCustomer(username);
    }
}
