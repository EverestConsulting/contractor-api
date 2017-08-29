package com.contractor.api;

import com.contractor.filter.Secured;
import com.contractor.model.enums.UserRights;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

/**
 * Handles the RESTFull API interface
 * All CRUD entry points are handled here.
 */
@Path("v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ApiController {
    private static final Logger LOG = LoggerFactory.getLogger(ApiController.class.getSimpleName());

    //login entry points
    //

    @POST
    @Secured({UserRights.login})
    @Path("/login")
    public Response login(@Context SecurityContext securityContext) {
        return Response.noContent().build();
    }

    @DELETE
    @Secured({UserRights.logout})
    @Path("/logout")
    public Response logout(@Context SecurityContext securityContext) {
        return Response.noContent().build();
    }

    //users api entry point
    //
    @GET
    @Secured({UserRights.getUser})
    @Path("/user")
    public Response getUser(@Context SecurityContext securityContext) {
        return Response.noContent().build();
    }

    @POST
    @Secured({UserRights.postUser})
    @Path("/user")
    public Response postUser(@Context SecurityContext securityContext) {
        return Response.noContent().build();
    }

    @PUT
    @Secured({UserRights.putUser})
    @Path("/user")
    public Response putUser(@Context SecurityContext securityContext) {
        return Response.noContent().build();
    }

    @DELETE
    @Secured({UserRights.deleteUser})
    @Path("/user")
    public Response deleteUser(@Context SecurityContext securityContext) {
        return Response.noContent().build();
    }

    //jobs api entry point
    //
    @GET
    @Secured({UserRights.getJob})
    @Path("/user")
    public Response getJob(@Context SecurityContext securityContext) {
        return Response.noContent().build();
    }

    @POST
    @Secured({UserRights.postJob})
    @Path("/user")
    public Response postJob(@Context SecurityContext securityContext) {
        return Response.noContent().build();
    }

    @PUT
    @Secured({UserRights.putJob})
    @Path("/user")
    public Response putJob(@Context SecurityContext securityContext) {
        return Response.noContent().build();
    }

    @DELETE
    @Secured({UserRights.deleteJob})
    @Path("/user")
    public Response deleteJob(@Context SecurityContext securityContext) {
        return Response.noContent().build();
    }

}