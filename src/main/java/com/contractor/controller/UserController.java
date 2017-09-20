package com.contractor.controller;

import com.contractor.App;
import com.contractor.api.ResponseFactory;
import com.contractor.model.dao.UserDao;
import com.contractor.model.entity.UserRole;
import com.contractor.model.entity.Users;
import com.contractor.model.request.RegistrationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;

/**
 * Singleton class which handles business logic for handling user operations.
 */
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class.getSimpleName());
    private static UserController INSTANCE;
    private UserDao USER_DAO;

    private UserController() {
        //singleton pattern
        USER_DAO = App.instance().getUserDao();
    }

    public static void init() {
        if (null != INSTANCE) {
            LOG.error(String.format("%s already initialized!!!", UserController.class.getSimpleName()));
            return;
        }

        INSTANCE = new UserController();
    }

    public static UserController instance() {
        if (null == INSTANCE) {
            LOG.error(String.format("%s not properly initialized!!!", UserController.class.getSimpleName()));
        }
        return INSTANCE;
    }

    public Response createUser(RegistrationRequest request) {

        Integer userRoleId = null;

        for (UserRole userRole : App.instance().getUserRoles()) {
            if (userRole.getUserRoleName().equalsIgnoreCase(request.getUserRole())) {
                userRoleId = userRole.getUserRoleId();
            }
        }

        if (null == userRoleId) {
            return ResponseFactory.getBadRequest400("Bad Request", "Missing mandatory userRole");
        }

        Users user = new Users();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setUserId(userRoleId);

        Integer userId = USER_DAO.create(user);

        if (null == userId) {
            return ResponseFactory.getInternalError500();
        }

        user.setUserId(userId);
        return ResponseFactory.getSuccess200(user);
    }

    public Response fetchUser(Integer userId) {
        Users user = USER_DAO.get(userId);

        if (null == user) {
            return ResponseFactory.getNotFound404();
        }

        return ResponseFactory.getSuccess200(user);
    }

    public Response updateUser(Users user) {
        boolean success = USER_DAO.update(user);

        if (!success) {
            return ResponseFactory.getBadRequest400("Couldn't update user", "Make sure all fields are present");
        }

        return ResponseFactory.getSuccess200(user);
    }

    public Response deleteUser(Integer userId) {
        Users user = USER_DAO.get(userId);

        if (null == user) {
            return ResponseFactory.getNotFound404();
        }


        if (USER_DAO.delete(user)) {
            return ResponseFactory.getSuccess200();
        }

        return ResponseFactory.getInternalError500();
    }

}
