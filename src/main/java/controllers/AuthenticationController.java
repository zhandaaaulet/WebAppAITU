package controllers;

import domain.User;
import org.glassfish.jersey.media.multipart.FormDataParam;
import services.AuthenticationService;
import services.interfaces.IAuthenticationService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("sign_up")
public class AuthenticationController {
    private final IAuthenticationService signupService = new AuthenticationService();

    @GET
    public String index() {
        return "Hello from AuthController";
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/add")
    public Response signUp(User data){
        try {
            signupService.createUserByIssue(data);

        } catch (Exception e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();

        }
        return Response.
                status(Response.Status.CREATED)
                .entity("User created successfully!")
                .build();

    }


}