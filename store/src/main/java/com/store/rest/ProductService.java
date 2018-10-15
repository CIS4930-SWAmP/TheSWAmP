package com.store.rest;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

import com.store.dao.*;
import com.store.model.*;

@Service
public class ProductService {


    //@Autowired
    private ProductDAO productDAO = new ProductDAO();


    public String getMsg( String msg) {
        return "Hello : " + msg;
    }

    public Collection<Product> getAllProducts() {

        Collection<Product> products = productDAO.getAllProducts();
        return products;
    }

    public Collection<Product> getItemsByKeyword(String keyword){
        return productDAO.getProductsByKeyword(keyword);
    }

    public Product getProductById(int id){
        return productDAO.getProductById(id);
    }
}
