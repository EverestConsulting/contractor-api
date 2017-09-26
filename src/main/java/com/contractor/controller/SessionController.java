package com.contractor.controller;

import com.contractor.App;
import com.contractor.api.ResponseFactory;
import com.contractor.model.dao.SessionTokenDao;
import com.contractor.model.dao.UserDao;
import com.contractor.model.entity.SessionToken;
import com.contractor.model.entity.Users;
import com.contractor.enums.DBProperty;
import com.contractor.model.request.LoginRequest;
import com.contractor.model.response.LoginResponse;
import com.contractor.util.Crypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import java.sql.Timestamp;


/**
 * Singleton class which handles business logic for handling session operations (login/logout).
 */
public class SessionController {

    private static final Logger LOG = LoggerFactory.getLogger(SessionController.class.getSimpleName());
    private static SessionController INSTANCE;
    private SessionTokenDao SESSION_TOKEN_DAO;
    private UserDao USER_DAO;

    private SessionController() {
        //singleton pattern
        SESSION_TOKEN_DAO = App.instance().getSessionTokenDao();
        USER_DAO = App.instance().getUserDao();
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

    public Response login(LoginRequest loginRequest) {
        //fetch user
        Users user = USER_DAO.getByParam(DBProperty.email, loginRequest.getEmail());
        //check credentials
        boolean userValid = null != user && Crypt.hashMatches(loginRequest.getPassword(), user.getPassword());

        //if we couldn't find user, return not found response
        if (!userValid) {
            return ResponseFactory.getNotFound404("Couldn't find user", "Email or password not correct!");
        }

        //otherwise create token and return it to user
        SessionToken sessionToken = new SessionToken();
        sessionToken.setSessionToken(Crypt.generateToken());
        sessionToken.setUserId(user.getUserId());
        sessionToken.setCreated(new Timestamp(System.currentTimeMillis()));
        sessionToken.setValidity(new Timestamp(sessionToken.getCreated().getTime() + 15 * 60 * 1000));

        Integer id = SESSION_TOKEN_DAO.create(sessionToken);


        if (null == id) {
            return ResponseFactory.getInternalError500();
        }

        return ResponseFactory.getSuccess200(new LoginResponse(sessionToken.getSessionToken(),
                sessionToken.getCreated().getTime(),
                sessionToken.getValidity().getTime()));
    }

    public Response logout(Integer userId) {
        SessionToken sessionToken = SESSION_TOKEN_DAO.getByParam(DBProperty.userId, userId);
        if (null != sessionToken) {
            SESSION_TOKEN_DAO.delete(sessionToken);
        }

        return ResponseFactory.getSuccess200();
    }

}
