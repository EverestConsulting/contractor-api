package com.contractor.api.v1;

import com.contractor.filter.Secured;
import com.contractor.model.enums.UserRights;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("/job-type")
public class JobTypeResource {

    @GET
    @Secured({UserRights.getJobType})
    public Response getJobType(@Context SecurityContext securityContext) {
        //TODO
        return Response.noContent().build();
    }

    @POST
    @Secured({UserRights.postJobType})
    public Response postJobType(@Context SecurityContext securityContext) {
        //TODO
        return Response.noContent().build();
    }

    @PUT
    @Secured({UserRights.putJobType})
    @Path("/{jobTypeId}")
    public Response putJobType(@Context SecurityContext securityContext, @NotNull @PathParam("jobTypeId") Integer jobTypeId) {
        //TODO
        return Response.noContent().build();
    }

    @DELETE
    @Secured({UserRights.deleteJobType})
    @Path("/{jobTypeId}")
    public Response deleteJobType(@Context SecurityContext securityContext,
                                  @NotNull @PathParam("jobTypeId") Integer jobTypeId) {
        //TODO
        return Response.noContent().build();
    }
}
