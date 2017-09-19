package com.contractor;


import com.contractor.model.dao.UserRightDao;
import com.contractor.model.dao.UserRoleDao;
import com.contractor.model.dao.impl.RightDao;
import com.contractor.model.dao.impl.RoleDao;
import com.contractor.model.dao.impl.SessionDao;
import com.contractor.model.dao.impl.UserDao;
import com.contractor.model.entity.UserRight;
import com.contractor.model.entity.UserRole;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Singleton class which handles constants fetched from db used across the app (ex. rights and roles).
 */
public class App {
    private static final Logger LOG = LoggerFactory.getLogger(App.class.getSimpleName());
    private static App INSTANCE;

    private Map<Integer, Set<Integer>> rolesAndRights;
    private Map<Integer, UserRole> userRoles;
    private Map<Integer, UserRight> userRights;

    private RightDao userRightDao;
    private RoleDao userRoleDao;
    private SessionDao sessionTokenDao;
    private UserDao userDao;

    private App() {
        //singleton

        userRightDao = new RightDao();
        userRoleDao = new RoleDao();
        sessionTokenDao = new SessionDao();
        userDao = new UserDao();
        LOG.info(String.format("%s initialized!!!", App.class.getSimpleName()));
    }

    /**
     * init singleton.
     */
    static void init() {
        if (null != INSTANCE) {
            LOG.error(String.format("%s already initialized!!!", App.class.getSimpleName()));
            return;
        }

        INSTANCE = new App();
    }


    /**
     * @return App instance
     */
    public static App instance() {
        if (null == INSTANCE) {
            LOG.error(String.format("%s not properly initialized!!!", App.class.getSimpleName()));
        }
        return INSTANCE;
    }

    /**
     * Loads all app constants.
     */
    private void loadConfig() {
        rolesAndRights = new HashMap<>(0);
        userRoles = new HashMap<>(0);
        userRights = new HashMap<>(0);

        userRoles = new UserRoleDao().fetchUserRoles();
        userRights = new UserRightDao().fetchUserRights();

        if (null == userRoles) {
            LOG.error("User Roles empty");
            return;
        }

        if (null == userRights) {
            LOG.error("User Rights empty");
            return;
        }

        for (Map.Entry<Integer, UserRole> roleEntry : userRoles.entrySet()) {
            rolesAndRights.put(roleEntry.getKey(), (Set<Integer>) roleEntry.getValue().getUserRight());
        }
    }

    /**
     * Clear all app constants and reload them.
     */
    public void reloadConfig() {
        rolesAndRights.clear();
        userRoles.clear();
        userRights.clear();

        loadConfig();
    }

    /**
     * @return Map with user rights and roles.
     */
    public Map<Integer, Set<Integer>> getRolesAndRights() {
        return rolesAndRights;
    }

    /**
     * @return Map with user roles
     */
    public Map<Integer, UserRole> getUserRoles() {
        return userRoles;
    }

    /**
     * @return Map with user rights
     */
    public Map<Integer, UserRight> getUserRights() {
        return userRights;
    }

    /**
     *
     * @return
     */
    public RightDao getUserRightDao() {
        return userRightDao;
    }

    /**
     *
     * @return
     */
    public RoleDao getUserRoleDao() {
        return userRoleDao;
    }

    /**
     *
     * @return
     */
    public SessionDao getSessionTokenDao() {
        return sessionTokenDao;
    }

    /**
     *
     * @return
     */
    public UserDao getUserDao() {
        return userDao;
    }
}
