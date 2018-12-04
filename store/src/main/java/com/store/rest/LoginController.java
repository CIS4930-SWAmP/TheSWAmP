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
import java.util.UUID;

@Controller
@Path("login")
public class LoginController extends HttpServlet{
    private LoginService loginService = new LoginService();
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
    public Response createLogin(
            @QueryParam("username") String username,
            @QueryParam("password") String password) {

        User user = userService.readUser(username);
        if(!(password.equals(user.getPassword()))) {
            String output = "Invalid password. Throwing 403 error.";
            return Response.status(403).entity(output).build();
        }
        else {
            UUID idOne = UUID.randomUUID();
            String sessionId = idOne.toString();
            String toReturn = "Session ID: " + sessionId + " Username: " + username + " User ID: " + user.getId();
            loginService.createLogin(sessionId, username, user.getId());
            return Response.status(200).entity(toReturn).build();
        }
    }

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Login readLogin(@QueryParam("sessionId") String sessionId) {
        Login login = loginService.readLogin(sessionId);
        return login;
    }

    @DELETE
    @Path("/{params}")
    public Response deleteUser(@PathParam("params") String sessionId){
        String output = "Deleting session " + sessionId;
        loginService.deleteLogin(sessionId);
        return Response.status(200).entity(output).build();
    }
}