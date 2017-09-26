package com.contractor.api.v1;

import com.contractor.controller.JobsController;
import com.contractor.filter.Secured;

import com.contractor.model.entity.Job;
import com.contractor.enums.UserRights;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("v1/job")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class JobResource {

    ////////////////////////
    //jobs api entry point//
    ///////////////////////
    @GET
    @Secured({UserRights.getJob})
    @Path("/{jobId}")
    public Response getJob(@Context SecurityContext securityContext,
                           @NotNull @PathParam("jobId") Integer jobId) {

        return JobsController.instance().fetchJob(jobId);
    }

    @POST
    @Secured({UserRights.postJob})
    public Response postJob(@Context SecurityContext securityContext,
                            @NotNull Job job) {
        return JobsController.instance().createJob(job);
    }

    @PUT
    @Secured({UserRights.putJob})
    @Path("/{jobId}")
    public Response putJob(@Context SecurityContext securityContext,
                           @NotNull @PathParam("jobId") Integer jobId,
                           @NotNull Job job) {
        return JobsController.instance().updateJob(jobId, job);
    }

    @DELETE
    @Secured({UserRights.deleteJob})
    @Path("/{jobId}")
    public Response deleteJob(@Context SecurityContext securityContext,
                              @PathParam("jobId") Integer jobId) {

        return JobsController.instance().deleteJob(jobId);
    }
}
