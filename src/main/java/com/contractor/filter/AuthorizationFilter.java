package com.contractor.filter;

import com.contractor.model.entity.user.User;
import com.contractor.model.enums.UserRights;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.EnumUtils;

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
//
//        // Get the resource method which matches with the requested URL
//        // Extract the rights declared by it
        Method resourceMethod = resourceInfo.getResourceMethod();
        List<UserRights> methodRights = extractRights(resourceMethod);
//
        try {
            // User.fetchUserById(requestContext.getSecurityContext().getUserPrincipal().getName());
            //TODO implement above use case when db ready
            User user = null;

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

    private void checkPermissions(List<UserRights> allowedRights, @NotNull User user) throws SecurityException {
        // Check if the user contains one of the allowed rights
        // Throw an Exception if the user has not permission to execute the method
        boolean hasRight = false;

        Set<UserRights> userRights = new HashSet<>();

        //TODO uncomment when db ready
        //fetch list of user rights by user roles
//        if (!ArrayUtils.isEmpty(user.getRoles())) {
//            for (String userRole : user.getRoles()) {
//                if (EnumUtils.isValidEnum(UserRoles.class, userRole)) {
//                    userRights.addAll(App.instance().getUserRolesMapping().get(UserRoles.valueOf(userRole)));
//                }
//            }
//        }
//
//        //check if user has some additional user rights
//        if (!ArrayUtils.isEmpty(user.getRights())) {
//            for (String userRight : user.getRights()) {
//                if (EnumUtils.isValidEnum(UserRights.class, userRight)) {
//                    userRights.add(UserRights.valueOf(userRight));
//                }
//            }
//        }

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
