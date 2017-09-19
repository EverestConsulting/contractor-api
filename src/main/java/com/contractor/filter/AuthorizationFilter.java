package com.contractor.filter;

import com.contractor.App;
import com.contractor.model.dao.UsersDao;
import com.contractor.model.entity.UserRight;
import com.contractor.model.entity.Users;
import com.contractor.model.enums.UserRights;
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
import java.security.Principal;
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
            Principal principal = requestContext.getSecurityContext().getUserPrincipal();
            String principalName = principal.getName();
            Users user = new UsersDao().fetchUserById(Long.valueOf(principalName));

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

        Set<Integer> rightsByCurrentUserRole = App.instance().getRolesAndRights().get((int) user.getUserRoleByUserRoleId().getUserRoleId());

        Set<UserRights> userRights = new HashSet<>(0);

        for (Integer userRightId : rightsByCurrentUserRole) {
            UserRight userRight = App.instance().getUserRights().get(userRightId);
            if (EnumUtils.isValidEnum(UserRights.class, userRight.getUserRight())) {
                userRights.add(UserRights.valueOf(userRight.getUserRight()));
            }
        }

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
