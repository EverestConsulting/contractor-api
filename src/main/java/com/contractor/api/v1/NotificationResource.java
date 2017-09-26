package com.contractor.api.v1;

import com.contractor.controller.NotificationController;
import com.contractor.enums.UserRights;
import com.contractor.filter.Secured;
import com.contractor.model.entity.NotificationToken;

import javax.validation.constraints.NotNull;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("/notification")
@Secured(UserRights.notificationManager)
public class NotificationResource {

    @POST
    public Response postNotificationToken(@Context SecurityContext securityContext,
                                          @NotNull NotificationToken notificationToken) {
        return NotificationController.instance().createNotificationToken(notificationToken);
    }

    @DELETE
    public Response removeNotificationToken(@Context SecurityContext securityContext) {
        return NotificationController.instance().deleteNotificationToken(Integer.valueOf(securityContext.getUserPrincipal().getName()));
    }
}
