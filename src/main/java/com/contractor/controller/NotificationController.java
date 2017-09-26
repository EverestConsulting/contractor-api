package com.contractor.controller;

import com.contractor.App;
import com.contractor.api.ResponseFactory;
import com.contractor.enums.DBProperty;
import com.contractor.model.dao.NotificationTokenDao;
import com.contractor.model.entity.NotificationToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;

public class NotificationController {
    private static final Logger LOG = LoggerFactory.getLogger(NotificationController.class.getSimpleName());
    private static NotificationController INSTANCE;
    private NotificationTokenDao NOTIFICATION_TOKEN_DAO;

    private NotificationController() {
        //singleton pattern
        NOTIFICATION_TOKEN_DAO = App.instance().getNotificationTokenDao();
    }

    public static void init() {
        if (null != INSTANCE) {
            LOG.error(String.format("%s already initialized!!!", NotificationController.class.getSimpleName()));
            return;
        }

        INSTANCE = new NotificationController();
    }

    public static NotificationController instance() {
        if (null == INSTANCE) {
            LOG.error(String.format("%s not properly initialized!!!", NotificationController.class.getSimpleName()));
        }
        return INSTANCE;
    }

    public Response createNotificationToken(NotificationToken notificationToken) {
        Integer id;

        id = NOTIFICATION_TOKEN_DAO.create(notificationToken);

        if (null == id) {
            return ResponseFactory.getInternalError500();
        }

        return ResponseFactory.getSuccess200();
    }

    NotificationToken getNotificationToken(Integer userId) {
        return NOTIFICATION_TOKEN_DAO.getByParam(DBProperty.userId, userId);
    }


    public Response deleteNotificationToken(Integer userId) {

        NotificationToken notificationToken = getNotificationToken(userId);
        NOTIFICATION_TOKEN_DAO.delete(notificationToken);

        return ResponseFactory.getSuccess200();
    }
}
