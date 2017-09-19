package com.contractor.api.v1;


import com.contractor.api.ResponseFactory;
import com.contractor.controller.SessionController;

import com.contractor.filter.Secured;

import com.contractor.model.enums.UserRights;
import com.contractor.model.request.LoginRequest;
import com.contractor.model.response.VersionInfo;
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
//@Path("v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ApiResources {
    private static final Logger LOG = LoggerFactory.getLogger(ApiResources.class.getSimpleName());

    @GET
    @Path("/version-info")
    public Response getVersionInfo() {
        return ResponseFactory.getSuccess200(new VersionInfo());
    }

    //////////////////////
    //login entry points//
    //////////////////////

    @POST
    @Path("/login")
    public Response login(@NotNull LoginRequest loginRequest) {
        return SessionController.getInstance().login(loginRequest);
    }

    @DELETE
    @Secured({UserRights.logout})
    @Path("/logout")
    public Response logout(@Context SecurityContext securityContext) {
        return SessionController.getInstance().logout(securityContext.getUserPrincipal().getName());
    }


}