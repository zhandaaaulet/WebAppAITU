package controllers;

import domain.AccessToken;
import domain.SignInData;
import services.AuthorizationService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("auth")
public class AuthorizationController {
    private final AuthorizationService authService = new AuthorizationService();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response signIn(SignInData data) {
        try {
            AccessToken token = authService.signIn(data);
            return Response.ok(token).build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

    }

}
