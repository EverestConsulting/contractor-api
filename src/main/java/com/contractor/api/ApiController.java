package com.contractor.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Handles the RESTFull API interface
 * All CRUD entry points are handled here.
 */
@Path("v1")
@Consumes("application/json")
@Produces("application/json;charset=utf-8")
public class ApiController {

    private static final Logger LOG = LoggerFactory.getLogger(ApiController.class.getSimpleName());
}