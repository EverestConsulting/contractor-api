package com.contractor.filter;

import com.contractor.App;
import com.contractor.model.entity.Users;
import com.contractor.model.enums.UserRights;

import javax.annotation.Priority;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.*;

@Secured
@Provider
@Priority(Priorities.AUTHORIZATION)
public class AuthorizationFilter implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        // Get the resource class which matches with the requested URL
        // Extract the rights declared by it
        Class<?> resourceClass = resourceInfo.getResourceClass();
        List<UserRights> classRights = extractRights(resourceClass);

        // Get the resource method which matches with the requested URL
        // Extract the rights declared by it
        Method resourceMethod = resourceInfo.getResourceMethod();
        List<UserRights> methodRights = extractRights(resourceMethod);

        try {
            Users user = App.instance().getUserDao().get(Integer.valueOf(requestContext.getSecurityContext().getUserPrincipal().getName()));

            if (null == user) {
                throw new SecurityException("Could not find user");
            }

            // Check if the user is allowed to execute the method
            // The method annotations override the class annotations
            checkPermissions((!methodRights.isEmpty()) ? methodRights : classRights, user);

        } catch (SecurityException e) {
            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

    // Extract rights from the annotated element
    private List<UserRights> extractRights(AnnotatedElement annotatedElement) {
        if (annotatedElement == null) {
            return new ArrayList<>();
        } else {
            Secured secured = annotatedElement.getAnnotation(Secured.class);
            if (secured == null) {
                return new ArrayList<>();
            } else {
                UserRights[] allowedRights = secured.value();
                return Arrays.asList(allowedRights);
            }
        }
    }

    private void checkPermissions(List<UserRights> allowedRights, @NotNull Users user) throws SecurityException {
        // Check if the user contains one of the allowed rights
        // Throw an Exception if the user has not permission to execute the method
        boolean hasRight = false;

        List<UserRights> userRights = App.instance().getRolesAndRights().get(user.getUserRoleId());

        //match user rights with required method rights
        for (UserRights allowedRight : allowedRights) {
            if (userRights.contains(allowedRight)) {
                hasRight = true;
            }
        }

        if (!hasRight) {
            throw new SecurityException("User does not have required access rights");
        }
    }
}
