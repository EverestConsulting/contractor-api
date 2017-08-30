package com.contractor.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Singleton class which handles business logic for handling session operations (login/logout).
 */
public class SessionController {

    private static final Logger LOG = LoggerFactory.getLogger(SessionController.class.getSimpleName());
    private static SessionController INSTANCE;

    private SessionController() {
        //singleton pattern
    }

    public static void init() {
        if (null != INSTANCE) {
            LOG.error(String.format("%s already initialized!!!", SessionController.class.getSimpleName()));
            return;
        }

        INSTANCE = new SessionController();
    }

    public static SessionController getInstance() {
        if (null == INSTANCE) {
            LOG.error(String.format("%s not properly initialized!!!", SessionController.class.getSimpleName()));
        }
        return INSTANCE;
    }

    public Object login() {
        return null;
    }

    public Object logout() {
        return null;
    }

}
