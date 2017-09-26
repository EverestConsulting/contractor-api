package com.contractor.api.v1;

import com.contractor.controller.CompanyController;
import com.contractor.model.entity.Company;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("/v1/company")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CompanyResource {

    @GET
    @Path("/{companyId}")
    public Response getCompany(@Context SecurityContext securityContext,
                               @PathParam("companyId") Integer companyId) {
        return CompanyController.instance().getCompany(companyId);
    }

    @POST
    public Response postCompany(@Context SecurityContext securityContext,
                                @NotNull Company company) {
        return CompanyController.instance().createCompany(company);
    }

    @PUT
    public Response putCompany(@Context SecurityContext securityContext,
                               @NotNull Company company) {
        return CompanyController.instance().updateCompany(company);
    }

    @DELETE
    @Path("/{companyId}")
    public Response deleteCompany(@Context SecurityContext securityContext,
                                  @NotNull @PathParam("companyId") Integer companyId) {
        return CompanyController.instance().deleteCompany(companyId);
    }

}
