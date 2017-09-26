package com.contractor;


import com.contractor.model.dao.*;

import com.contractor.model.entity.UserRole;
import com.contractor.enums.UserRights;
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

    private CompanyDao companyDao;
    private JobDao jobDao;
    private JobStatusDao jobStatusDao;
    private JobTypeDao jobTypeDao;
    private NotificationTokenDao notificationTokenDao;
    private PricingDao pricingDao;
    private PricingPlanDao pricingPlanDao;
    private SessionTokenDao sessionTokenDao;
    private UserDao userDao;
    private UserRoleDao userRoleDao;

    private App() {

        //instantiate data access singleton classes
        companyDao = new CompanyDao();
        jobDao = new JobDao();
        jobStatusDao = new JobStatusDao();
        jobTypeDao = new JobTypeDao();
        notificationTokenDao = new NotificationTokenDao();
        pricingDao = new PricingDao();
        pricingPlanDao = new PricingPlanDao();
        sessionTokenDao = new SessionTokenDao();
        userDao = new UserDao();
        userRoleDao = new UserRoleDao();

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

    public CompanyDao getCompanyDao() {
        return companyDao;
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

    public NotificationTokenDao getNotificationTokenDao() {
        return notificationTokenDao;
    }

    public PricingDao getPricingDao() {
        return pricingDao;
    }

    public PricingPlanDao getPricingPlanDao() {
        return pricingPlanDao;
    }

    public SessionTokenDao getSessionTokenDao() {
        return sessionTokenDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public UserRoleDao getUserRoleDao() {
        return userRoleDao;
    }
}
