package com.contractor.api.v1;


import com.contractor.App;
import com.contractor.api.ResponseFactory;
import com.contractor.controller.SessionController;

import com.contractor.filter.Secured;

import com.contractor.model.entity.*;
import com.contractor.enums.UserRights;
import com.contractor.model.request.LoginRequest;
import com.contractor.model.response.LoginResponse;
import com.contractor.model.response.VersionInfo;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

/**
 * Handles the RESTFull API interface
 * All CRUD entry points are handled here.
 */
@Path("v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ApiResources {

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
    @Secured(UserRights.logout)
    @Path("/logout")
    public Response logout(@Context SecurityContext securityContext) {
        return SessionController.getInstance().logout(Integer.valueOf(securityContext.getUserPrincipal().getName()));
    }

    @POST
    @Path("/password-reset")
    public Response passwordReset(@NotNull LoginResponse loginResponse) {
        return ResponseFactory.getNotImplemented501();
    }

    @GET
    @Path("/category")
    public Response getCategories() {
        List<JobType> jobTypeList = App.instance().getJobTypeDao().findAll();

        return null == jobTypeList ?
                ResponseFactory.getNotImplemented501()
                : ResponseFactory.getSuccess200(jobTypeList);
    }

    @GET
    @Path("/job-status")
    public Response getJobStatus(@Context SecurityContext securityContext) {
        List<JobStatus> jobStatusList = App.instance().getJobStatusDao().findAll();
        return null == jobStatusList ?
                ResponseFactory.getNotImplemented501()
                : ResponseFactory.getSuccess200(jobStatusList);
    }

    @GET
    @Path("/pricing-plan")
    public Response getPricingPlan() {
        List<PricingPlan> pricingPlanList = App.instance().getPricingPlanDao().findAll();
        return null == pricingPlanList ?
                ResponseFactory.getNotImplemented501()
                : ResponseFactory.getSuccess200(pricingPlanList);
    }

    @GET
    @Path("/pricing")
    public Response getPricing() {
        List<Pricing> pricingList = App.instance().getPricingDao().findAll();
        return null == pricingList ?
                ResponseFactory.getNotImplemented501()
                : ResponseFactory.getSuccess200(pricingList);
    }

}