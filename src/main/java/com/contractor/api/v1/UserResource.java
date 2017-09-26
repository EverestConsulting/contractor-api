package com.contractor.api.v1;

import com.contractor.controller.UserController;
import com.contractor.filter.Secured;
import com.contractor.model.entity.Users;
import com.contractor.enums.UserRights;
import com.contractor.model.request.RegistrationRequest;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.sql.Timestamp;

@Path("v1/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    /////////////////////////
    //users api entry point//
    /////////////////////////
    @GET
    @Secured({UserRights.getUser})
    public Response getUser(@Context SecurityContext securityContext) {
        return UserController.instance().fetchUser(Integer.valueOf(securityContext.getUserPrincipal().getName()));
    }

    @POST
    public Response postUser(@NotNull RegistrationRequest registrationRequest) {
        return UserController.instance().createUser(registrationRequest);
    }

    @PUT
    @Secured({UserRights.putUser})
    public Response putUser(@Context SecurityContext securityContext,
                            @NotNull Users user) {
        user.setLastModified(new Timestamp(System.currentTimeMillis()));
        return UserController.instance().updateUser(user);
    }

    @DELETE
    @Secured({UserRights.deleteUser})
    @Path("/{userId}")
    public Response deleteUser(@Context SecurityContext securityContext,
                               @NotNull @PathParam("userId") Integer id) {
        return UserController.instance().deleteUser(id);
    }
}
