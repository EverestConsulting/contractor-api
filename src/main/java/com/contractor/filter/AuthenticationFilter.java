package com.contractor.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.security.Principal;

/**
 *
 */
@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationFilter.class.getSimpleName());

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        // Get the HTTP Authorization header from the request
//        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
//
//        String path = requestContext.getUriInfo().getPath();
//
//        TokenSession tokenSessionLocalhost = null;
//
//        if (path.endsWith("/admin-console") && null != requestContext.getHeaderString("Host") && requestContext.getHeaderString("Host").startsWith("localhost")) {
//            // Let localhost user through to the admin console!
//            tokenSessionLocalhost = User.matchUser(new TechnicalLoginRequest("bnd_v1_bnd_localhost", "nopassword"), true);
//        } else if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
//            // HTTP Authorization header is not present or formatted correctly
//            LOG.warn("Unauthorised access, missing valid Authorization header!");
//            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
//            return;
//        }
//
//        // Extract the token from the HTTP Authorization header
//        String token = null != authorizationHeader ? authorizationHeader.substring("Bearer".length()).trim() : null;
//
//        try {
//
//            // Validate the token
//            final TokenSession tokenSession = null != tokenSessionLocalhost ? tokenSessionLocalhost : TokenSession.findByToken(token);
//
//            if (null == tokenSession) {
//                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
//                return;
//            }
//
//            requestContext.setSecurityContext(new SecurityContext() {
//
//                @Override
//                public Principal getUserPrincipal() {
//
//                    return new Principal() {
//
//                        @Override
//                        public String getName() {
//                            return tokenSession.getUserId();
//                        }
//                    };
//                }
//
//                @Override
//                public boolean isUserInRole(String role) {
//                    return true;
//                }
//
//                @Override
//                public boolean isSecure() {
//                    return false;
//                }
//
//                @Override
//                public String getAuthenticationScheme() {
//                    return tokenSession.getTokenType().name();
//                }
//            });
//
//        } catch (Exception e) {
//            LOG.warn(String.format("Unauthorised access with token: %s", token));
//            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
//        }
    }
}
