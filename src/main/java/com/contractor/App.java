package com.contractor;


import com.contractor.model.dao.UserRightDao;
import com.contractor.model.dao.UserRoleDao;
import com.contractor.model.dao.UsersDao;
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

    private App() {
        //singleton
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
    public static App getInstance() {
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
}
