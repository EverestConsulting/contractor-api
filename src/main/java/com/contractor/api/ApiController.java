package com.contractor.api;

import com.contractor.controller.JobsController;
import com.contractor.controller.SessionController;
import com.contractor.controller.UserController;
import com.contractor.filter.Secured;
import com.contractor.model.entity.Jobs;
import com.contractor.model.entity.Users;
import com.contractor.model.enums.UserRights;
import com.contractor.model.request.LoginRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
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

    //////////////////////
    //login entry points//
    //////////////////////

    @POST
    @Secured({UserRights.login})
    @Path("/login")
    public Response login(@Context SecurityContext securityContext,
                          @NotNull LoginRequest loginRequest) {

        //TODO: Implement fetching user from db base on LoginRequest. If no user matched return Not Found. If found create new token session and return 200 ok with LoginResponse.
//        SessionController.getInstance().login(loginRequest);
        return Response.noContent().build();
    }

    @DELETE
    @Secured({UserRights.logout})
    @Path("/logout")
    public Response logout(@Context SecurityContext securityContext) {

        //TODO Implement fetching SessionToken from db by calling security context and return 404 not found or 200 ok.
//        SessionController.getInstance().logout(securityContext.getUserPrincipal().getName());
        return Response.noContent().build();
    }

    /////////////////////////
    //users api entry point//
    /////////////////////////
    @GET
    @Secured({UserRights.getUser})
    @Path("/user")
    public Response getUser(@Context SecurityContext securityContext) {
        //TODO Implement passing id as user/id. Implement fetching user data by user id from security context.
//        UserController.getInstance().fetchUser(securityContext.getUserPrincipal().getName());
        return Response.noContent().build();
    }

    @POST
    @Secured({UserRights.postUser})
    @Path("/user")
    public Response postUser(@Context SecurityContext securityContext,
                             @NotNull Users user) {
        //TODO Implement creating new user logic/registration.
//        UserController.getInstance().createUser(securityContext.getUserPrincipal().getName(),user);
        return Response.noContent().build();
    }

    @PUT
    @Secured({UserRights.putUser})
    @Path("/user")
    public Response putUser(@Context SecurityContext securityContextt,
                            @NotNull Users user) {
        //TODO Implement updating user logic.
//        UserController.getInstance().updateUser(securityContext.getUserPrincipal().getName(),user);
        return Response.noContent().build();
    }

    @DELETE
    @Secured({UserRights.deleteUser})
    @Path("/user/{userId}")
    public Response deleteUser(@Context SecurityContext securityContext,
                               @NotNull @PathParam("userId") Integer id) {
        //TODO Implement deactivating/deleting user logic.
//        UserController.getInstance().deleteUser(securityContext.getUserPrincipal().getName(),id);
        return Response.noContent().build();
    }

    ////////////////////////
    //jobs api entry point//
    ///////////////////////
    @GET
    @Secured({UserRights.getJob})
    @Path("/job")
    public Response getJob(@Context SecurityContext securityContext) {
        //TODO Implement fetching jobs based on business logic.
//        JobsController.getInstance().fetchJob();
        return Response.noContent().build();
    }

    @POST
    @Secured({UserRights.postJob})
    @Path("/job")
    public Response postJob(@Context SecurityContext securityContext,
                            @NotNull Jobs job) {
        //TODO Implement creating jobs logic.
//        JobsController.getInstance().createJob()
        return Response.noContent().build();
    }

    @PUT
    @Secured({UserRights.putJob})
    @Path("/job/{jobId}")
    public Response putJob(@Context SecurityContext securityContext,
                           @NotNull @PathParam("jobId") Integer jobId,
                           @NotNull Jobs job) {
        //TODO Implement updating jobs logic.
//        JobsController.getInstance().updateJob();
        return Response.noContent().build();
    }

    @DELETE
    @Secured({UserRights.deleteJob})
    @Path("/job/{jobId}")
    public Response deleteJob(@Context SecurityContext securityContext,
                              @PathParam("jobId") Integer jobId) {
        //TODO Implement removing jobs logic.
//        JobsController.getInstance().deleteJob();
        return Response.noContent().build();
    }

    @GET
    @Secured({UserRights.getJobType})
    @Path("/job-type")
    public Response getJobType(@Context SecurityContext securityContext) {
        //TODO
        return Response.noContent().build();
    }

    @POST
    @Secured({UserRights.getJobType})
    @Path("/job-type")
    public Response postJobType(@Context SecurityContext securityContext) {
        //TODO
        return Response.noContent().build();
    }

    @PUT
    @Secured({UserRights.getJobType})
    @Path("/job-type/{jobTypeId}")
    public Response putJobType(@Context SecurityContext securityContext, @NotNull @PathParam("jobTypeId") Integer jobTypeId) {
        //TODO
        return Response.noContent().build();
    }

    @DELETE
    @Secured({UserRights.getJobType})
    @Path("/job-type/{jobTypeId}")
    public Response deleteJobType(@Context SecurityContext securityContext,
                                  @NotNull @PathParam("jobTypeId") Integer jobTypeId) {
        //TODO
        return Response.noContent().build();
    }

}