package controllers;


import domain.Product;
import services.SearchService;
import services.interfaces.ISearchService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("search")
public class SearchController {
    private final ISearchService searchService = new SearchService();

    @GET
    public String index() {
        return "Hello from SearchController";
    }


    @Path("/type")
    @GET
    public String index1() {
        return "Hello from Type, add '/' and {type} ";
    }


    @GET
    @Path("type/{type}")
    public Response getProductByType(@PathParam("type") String type){
        List<Product> product;
        try {
            product = searchService.searchProductByType(type);
        } catch (ServerErrorException e) {
            return Response.serverError().build();
        } catch (BadRequestException b) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }


        if (product == null) {
            return Response.ok("No Products").build();
        } else {
            return Response.ok(product).build();
        }
    }


    @GET
    @Path("/{param}")
    public Response getProductById(@PathParam("param") int id) {
        Product product;
        try {
            product = searchService.searchProductById(id);
        } catch (ServerErrorException e) {
            return Response.serverError().build();
        } catch (BadRequestException b) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        if (product == null) {
            return Response.ok("No Products").build();
        } else {
            return Response.ok(product).build();
        }


    }
    @Path("/all")
    @GET
    public String index2() {
        return "Hello from Type, add '/' and {request} to have all products by request ";
    }
    @GET
    @Path("all/{param}")
    public Response getProductByAll(@PathParam("param") String request){
        List<Product> product;
        try {
            product = searchService.searchByTag(request);
        } catch (ServerErrorException e) {
            return Response.serverError().build();
        } catch (BadRequestException b) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }


        if (product == null) {
            return Response.ok("No Products").build();
        } else {
            return Response.ok(product).build();
        }
    }




}