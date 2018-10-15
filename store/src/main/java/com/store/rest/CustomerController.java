package com.store.rest;

import javax.servlet.http.HttpServlet;
import javax.servlet.*;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;


import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

import com.store.model.*;

@Controller
@Path("customers")
public class CustomerController extends HttpServlet{
    private CustomerService customerService = new CustomerService();

    public void init(ServletConfig config) {
        try{
            super.init(config);
            SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                    config.getServletContext());
        }catch(ServletException e){
        }
    }

    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer getCustomer(@PathParam("username") String username){
        return customerService.getCustomer(username);
    }

//    @POST
//    @Path("")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response createCustomer(
//            @QueryParam("fname") String fname,
//            @QueryParam("lname") String lname,
//            @QueryParam("username") String username,
//            @QueryParam("email") String email){
//        if(customerService.createCustomer(fname, lname, username, email)) {
//            return Response.status(200).entity("hi").build();
//        }
//        return Response.status(400).build();
//    }

//    @PUT
//    @Path("")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response updateCustomer(
//            @QueryParam("fname") String fname,
//            @QueryParam("lname") String lname,
//            @QueryParam("username") String username,
//            @QueryParam("email") String email){
//        if(customerService.updateCustomer(fname, lname, username, email)){
//            return Response.status(200).build();
//        }
//        return Response.status(400).build();
//    }
}
