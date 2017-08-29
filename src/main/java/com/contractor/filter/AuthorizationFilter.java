package com.contractor.filter;

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

//        // Get the resource class which matches with the requested URL
//        // Extract the rights declared by it
//        Class<?> resourceClass = resourceInfo.getResourceClass();
//        List<UserRights> classRights = extractRights(resourceClass);
//
//        // Get the resource method which matches with the requested URL
//        // Extract the rights declared by it
//        Method resourceMethod = resourceInfo.getResourceMethod();
//        List<UserRights> methodRights = extractRights(resourceMethod);
//
//        try {
//            User user = User.fetchUserById(requestContext.getSecurityContext().getUserPrincipal().getName());
//
//            if (null == user && TokenType.hal.name().equals(requestContext.getSecurityContext().getAuthenticationScheme())) {
//                user = User.fetchByHalCustomerNumber(requestContext.getSecurityContext().getUserPrincipal().getName());
//
//                //This ensures that all users that can log in to hal can login to our system as well
//                //via our new authentication rules (accessing allowed entry points with HAL token),
//                //even if they our not yet imported to our db.
//                if (null == user) {
//                    user = new User(new String[]{UserRoles.user.name()});
//                }
//            }
//
//            if (null == user) {
//                throw new SecurityException("Could not find user");
//            }
//
//            // Check if the user is allowed to execute the method
//            // The method annotations override the class annotations
//            checkPermissions((!methodRights.isEmpty()) ? methodRights : classRights, user);
//
//        } catch (SecurityException e) {
//            requestContext.abortWith(
//                    Response.status(Response.Status.UNAUTHORIZED).build());
//        }
    }

    // Extract rights from the annotated element
//    private List<UserRights> extractRights(AnnotatedElement annotatedElement) {
//        if (annotatedElement == null) {
//            return new ArrayList<>();
//        } else {
//            Secured secured = annotatedElement.getAnnotation(Secured.class);
//            if (secured == null) {
//                return new ArrayList<>();
//            } else {
//                UserRights[] allowedRights = secured.value();
//                return Arrays.asList(allowedRights);
//            }
//        }
//    }

//    private void checkPermissions(List<UserRights> allowedRights, @NotNull User user) throws SecurityException {
//        // Check if the user contains one of the allowed rights
//        // Throw an Exception if the user has not permission to execute the method
//        boolean hasRight = false;
//
//        Set<UserRights> userRights = new HashSet<>();
//
//        //fetch list of user rights by user roles
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
//
//        //match user rights with required method rights
//        for (UserRights allowedRight : allowedRights) {
//            if (userRights.contains(allowedRight)) {
//                hasRight = true;
//            }
//        }
//
//        if (!hasRight) {
//            throw new SecurityException("User does not have required access rights");
//        }
//    }
}
