package controllers;

import domain.Order;
import filters.customAnnotations.JWTTokenNeeded;
import services.OrderService;
import services.interfaces.IOrderService;

import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;


@Path("order")
public class OrderController {
    private IOrderService orderService = new OrderService();

    @GET
    public String index() {
        return "Hello from Order Controller.Add to url /my to have load own orders";
    }

    @Path("/my")
    @JWTTokenNeeded
    @GET
    public Response getMyCartProducts(@Context ContainerRequestContext requestContext) {
        List<Order> orderProducts = new LinkedList<>();
        try {
            orderProducts = orderService.getMyCartProducts(requestContext.getSecurityContext().getUserPrincipal().getName());
        } catch (ServerErrorException e) {
            return Response.serverError().build();
        } catch (BadRequestException b) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        if (orderProducts == null) {
            return Response.ok("No orders yet").build();
        } else {
            return Response.ok(orderProducts).build();
        }


    }

    @POST
    @JWTTokenNeeded
    @Path("/add/{param}")
    public Response AddOrder(@PathParam("param") int id, @Context ContainerRequestContext requestContext) {
        try {
            orderService.addorder(id, requestContext.getSecurityContext().getUserPrincipal().getName());
        } catch (ServerErrorException e) {
            return Response.serverError().build();
        } catch (BadRequestException b) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.CREATED).entity("Order added").build();

    }


    @POST
    @JWTTokenNeeded
    @Path("/remove/{param}")
    public Response removeOrder(@PathParam("param") int id, @Context ContainerRequestContext requestContext) {
        try {
            orderService.removeOrder(id, requestContext.getSecurityContext().getUserPrincipal().getName());
        } catch (ServerErrorException e) {
            return Response.serverError().build();
        } catch (BadRequestException b) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.CREATED).entity("Order removed").build();
    }


}