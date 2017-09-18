package com.contractor.controller;

import com.contractor.model.dao.UsersDao;
import com.contractor.model.entity.Users;
import com.contractor.model.request.RegistrationRequest;
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

    public Object createUser(RegistrationRequest request) {
        Users user = new Users();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        return new UsersDao().createUser(user);
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
