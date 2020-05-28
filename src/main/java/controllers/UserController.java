package controllers;



import javax.ws.rs.GET;
import javax.ws.rs.Path;


@Path("user")
public class UserController {

    @GET
    public String hello() {
        return "Hello world!";
    }


}
