package com.contractor.api.v1;

import com.contractor.filter.Secured;
import com.contractor.model.entity.Jobs;
import com.contractor.model.enums.UserRights;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("/job")
public class JobResource {

    ////////////////////////
    //jobs api entry point//
    ///////////////////////
    @GET
    @Secured({UserRights.getJob})
    public Response getJob(@Context SecurityContext securityContext) {
        //TODO Implement fetching jobs based on business logic.
//        JobsController.instance().fetchJob();
        return Response.noContent().build();
    }

    @POST
    @Secured({UserRights.postJob})
    public Response postJob(@Context SecurityContext securityContext,
                            @NotNull Jobs job) {
        //TODO Implement creating jobs logic.
//        JobsController.instance().createJob()
        return Response.noContent().build();
    }

    @PUT
    @Secured({UserRights.putJob})
    @Path("/{jobId}")
    public Response putJob(@Context SecurityContext securityContext,
                           @NotNull @PathParam("jobId") Integer jobId,
                           @NotNull Jobs job) {
        //TODO Implement updating jobs logic.
//        JobsController.instance().updateJob();
        return Response.noContent().build();
    }

    @DELETE
    @Secured({UserRights.deleteJob})
    @Path("/{jobId}")
    public Response deleteJob(@Context SecurityContext securityContext,
                              @PathParam("jobId") Integer jobId) {
        //TODO Implement removing jobs logic.
//        JobsController.instance().deleteJob();
        return Response.noContent().build();
    }
}
