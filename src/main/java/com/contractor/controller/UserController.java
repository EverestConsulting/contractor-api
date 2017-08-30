package com.contractor.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Singleton class which handles business logic for handling user operations.
 */
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class.getSimpleName());
    private static UserController INSTANCE;

    private UserController() {
        //singleton pattern
    }

    public static void init() {
        if (null != INSTANCE) {
            LOG.error(String.format("%s already initialized!!!", UserController.class.getSimpleName()));
            return;
        }

        INSTANCE = new UserController();
    }

    public static UserController getInstance() {
        if (null == INSTANCE) {
            LOG.error(String.format("%s not properly initialized!!!", UserController.class.getSimpleName()));
        }
        return INSTANCE;
    }

    public Object createUser() {
        return null;
    }

    public Object fetchUser() {
        return null;
    }

    public Object updateUser() {
        return null;
    }

    public Object deleteUser() {
        return null;
    }

}
