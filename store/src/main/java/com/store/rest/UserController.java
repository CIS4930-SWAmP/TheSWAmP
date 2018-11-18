package com.store.rest;

import javax.servlet.http.HttpServlet;
import javax.servlet.*;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.stereotype.Controller;


import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Controller
@Path("carts")
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

    @GET
    @Path("")
    public Response showUsersCart(
            @QueryParam("username") String username,
            @QueryParam("productId") int productId){
        if(username!=null && productId == 0) {
            int cartId = userService.getCartId(username);
            if (cartId == 0) {
                return Response.status(409).build();
            }

            return Response.status(200).entity("[{\"cartId\": "+ cartId +",\n\"items\": " + userService.getCart(username).toString() + "}\n]").build();
        }
        else if(productId != 0 && username==null){
            return Response.status(200).entity(userService.usersBoughtAProduct(productId).toString()).build();
        }
        return Response.status(409).build();
    }

    @POST
    @Path("")
    public Response addItemToCart(@QueryParam("productId") int productId, @QueryParam("username") String username){
        if(userService.addItemToCart(productId, username) != null)
            return Response.status(200).build();
        return Response.status(409).build();
    }

    @DELETE
    @Path("")
    public Response deleteItem(@QueryParam("cartId") int cartId, @QueryParam("productId") int productId){
        if(userService.removeItem(cartId,productId))
            return Response.status(200).build();
        return Response.status(409).build();
    }

    @PUT
    @Path("/purchase/{cartId}")
    public Response purchaseCart(@PathParam("cartId") int cartId){
        if(userService.purchaseCart(cartId))
            return Response.status(200).build();
        return Response.status(409).build();
    }

}
