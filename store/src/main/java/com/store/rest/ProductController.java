package com.store.rest;

import javax.servlet.http.HttpServlet;
import javax.servlet.*;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

import com.store.model.*;

@Controller
@Path("items")
public class ProductController extends HttpServlet  {


    //@Autowired
    private ProductService productService = new ProductService();

    public void init(ServletConfig config) {
        try{
            super.init(config);
            SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                    config.getServletContext());
        }catch(ServletException e){
        }
    }


    //List all items
    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Product> getAllProducts() {
        Collection<Product> products = productService.getAllProducts();
        return products;
    }

    //List items by keyword
    @GET
    @Path("/search/{keyword}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Product> getItemsByKeyword(@PathParam("keyword") String keyword){
        return productService.getItemsByKeyword(keyword);
    }

    //List item by id
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Product getItemById(@PathParam("id") int id){
        return productService.getProductById(id);
    }
}