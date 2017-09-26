package com.contractor.api;


import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Initializes the JAX-RS RESTFull API (web.xml is not needed)
 */
@ApplicationPath("api")
public class Api extends Application {
//main entry for application paths
//version v1 path are under v1 package folder
//for next versions use same structure (create folder v2) while making sure all features working
//since we cannot guarantee that users updated mobile application.
}