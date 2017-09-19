package com.contractor.api.v1;

import com.contractor.controller.UserController;
import com.contractor.filter.Secured;
import com.contractor.model.entity.Users;
import com.contractor.model.enums.UserRights;
import com.contractor.model.request.RegistrationRequest;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    /////////////////////////
    //users api entry point//
    /////////////////////////
    @GET
    @Secured({UserRights.getUser})
    public Response getUser(@Context SecurityContext securityContext) {
        //TODO Implement passing id as user/id. Implement fetching user data by user id from security context.
//        UserController.instance().fetchUser(securityContext.getUserPrincipal().getName());
        return Response.noContent().build();
    }

    @POST
    @Secured({UserRights.postUser})
    public Response postUser(@NotNull RegistrationRequest registrationRequest) {
        UserController.getInstance().createUser(registrationRequest);
        return Response.noContent().build();
    }

    @PUT
    @Secured({UserRights.putUser})
    @Path("/{userId}")
    public Response putUser(@Context SecurityContext securityContext,
                            @NotNull @PathParam("userId") Integer userId,
                            @NotNull Users user) {
        //TODO Implement updating user logic.
//        UserController.instance().updateUser(securityContext.getUserPrincipal().getName(),user);
        return Response.noContent().build();
    }

    @DELETE
    @Secured({UserRights.deleteUser})
    @Path("/{userId}")
    public Response deleteUser(@Context SecurityContext securityContext,
                               @NotNull @PathParam("userId") Integer id) {
        //TODO Implement deactivating/deleting user logic.
//        UserController.instance().deleteUser(securityContext.getUserPrincipal().getName(),id);
        return Response.noContent().build();
    }
}
