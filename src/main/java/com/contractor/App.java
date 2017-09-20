package com.contractor;


import com.contractor.model.dao.*;

import com.contractor.model.entity.UserRole;
import com.contractor.model.enums.UserRights;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Singleton class which handles constants fetched from db used across the app (ex. rights and roles).
 */
public class App {
    private static final Logger LOG = LoggerFactory.getLogger(App.class.getSimpleName());
    private static App INSTANCE;

    private Map<Integer, List<UserRights>> rolesAndRights;
    private List<UserRole> userRoles;

    private JobDao jobDao;
    private JobStatusDao jobStatusDao;
    private JobTypeDao jobTypeDao;
    private PricingDao pricingDao;
    private PricingPlanDao pricingPlanDao;
    private UserRoleDao userRoleDao;
    private SessionTokenDao sessionTokenDao;
    private UserDao userDao;

    private App() {
        //singleton

        //instantiate data access singleton classes
        jobDao = new JobDao();
        jobStatusDao = new JobStatusDao();
        jobTypeDao = new JobTypeDao();
        pricingDao = new PricingDao();
        pricingPlanDao = new PricingPlanDao();
        userRoleDao = new UserRoleDao();
        sessionTokenDao = new SessionTokenDao();
        userDao = new UserDao();

        //load runtime variables
        loadConfig();
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
        userRoles = userRoleDao.findAll();

        if (null == userRoles || userRoles.size() <= 0) {
            LOG.error("User Roles empty");
            return;
        }

        for (UserRole role : userRoles) {
            rolesAndRights.put(role.getUserRoleId(), Arrays.asList(UserRights.values()));
        }
    }

    /**
     * Clear all app constants and reload them.
     */
    public void reloadConfig() {
        rolesAndRights.clear();
        userRoles.clear();

        loadConfig();
    }

    /**
     * @return Map with user rights and roles.
     */
    public Map<Integer, List<UserRights>> getRolesAndRights() {
        return rolesAndRights;
    }

    /**
     * @return Map with user roles
     */
    public List<UserRole> getUserRoles() {
        return userRoles;
    }


    public JobDao getJobDao() {
        return jobDao;
    }

    public JobStatusDao getJobStatusDao() {
        return jobStatusDao;
    }

    public JobTypeDao getJobTypeDao() {
        return jobTypeDao;
    }

    public PricingDao getPricingDao() {
        return pricingDao;
    }

    public PricingPlanDao getPricingPlanDao() {
        return pricingPlanDao;
    }


    /**
     * @return
     */
    public UserRoleDao getUserRoleDao() {
        return userRoleDao;
    }

    /**
     * @return
     */
    public SessionTokenDao getSessionTokenDao() {
        return sessionTokenDao;
    }

    /**
     * @return
     */
    public UserDao getUserDao() {
        return userDao;
    }
}
