package controllers;


import domain.Product;
import services.IProductService;
import services.ProductService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("products")
public class ProductController {
    private final ProductService productService = new ProductService();


    @GET
    public String index() {
        return "Hello from Product Controller";
    }

    @GET
    @Path("/{param}")
    public Response getProductByID(@PathParam("param") long id) {
        Product product;
        try {
            product = productService.getProductByID(id);
        } catch (ServerErrorException e) {
            return Response.serverError().build();
        } catch (BadRequestException b) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if (product == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(product).build();
        }

    }

}