package com.store.rest;

import javax.servlet.http.HttpServlet;
import javax.servlet.*;
import java.util.UUID;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.stereotype.Controller;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.Produces;
import java.util.Collection;
import java.util.ArrayList;


import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import com.store.model.*;
import com.store.dao.*;


@Controller
@Path("users")
public class UserController extends HttpServlet{
    private UserService userService = new UserService();

    public void init(ServletConfig config) {
        try{
            super.init(config);
            SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                    config.getServletContext());
        }catch(ServletException e){
        }
    }

    @POST
    @Path("")
    public Response createUser(
            @QueryParam("fname") String fname,
            @QueryParam("lname") String lname,
            @QueryParam("username") String username,
            @QueryParam("password") String password,
            @QueryParam("phone") String phone,
            @QueryParam("email") String email) {

        userService.createUser(username, password, phone, fname, lname, email, false);

        return Response
                .status(200)
                .entity("Created customer:  " + fname + " " + lname + " " +  username + " " +  email).build();
    }

    @GET
    @Path("/{params}")
    @Produces(MediaType.APPLICATION_JSON)
    public User readUser(@PathParam("params") String username){
        User user = userService.readUser(username);
        return user;
        //return Response.status(200).entity(output).build();
    }

    @PUT
    @Path("")
    public Response updateUser(
            @QueryParam("fname") String fname,
            @QueryParam("lname") String lname,
            @QueryParam("username") String username,
            @QueryParam("password") String password,
            @QueryParam("phone") String phone,
            @QueryParam("email") String email) {

        userService.updateUser(username, password, phone, fname, lname, email);

        return Response
                .status(200)
                .entity("Updated customer:  " + fname + " " + lname + " " +  username + " " +  email).build();

    }

    @DELETE
    @Path("/{params}")
    public Response deleteUser(@PathParam("params") String username){
        String output = "Deleting user " + username;
        userService.deleteUser(username);
        return Response.status(200).entity(output).build();
    }
}
