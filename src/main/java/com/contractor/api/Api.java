package com.contractor.api;

import com.contractor.api.v1.ApiResources;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Initializes the JAX-RS RESTFull API (web.xml is not needed)
 */
@ApplicationPath("api")
public class Api extends Application {
    //

    private Set<Object> singletons = new HashSet<>(0);

    public Api() {
        singletons.add(new ApiResources());
    }

    @Override
    public Set<Object> getSingletons() {
        return super.getSingletons();
    }
}