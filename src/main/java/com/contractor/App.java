package com.contractor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Singleton class which handles constants fetched from db used across the app (ex. rights and roles).
 */
public class App {
    private static final Logger LOG = LoggerFactory.getLogger(App.class.getSimpleName());
    private static App INSTANCE;

    private Map<String, List<String>> rightsAndRoles;

    private App() {
        LOG.info("%s initialized!!!");
    }

    /**
     * init singleton.
     */
    static void init() {
        if (null != INSTANCE) {
            LOG.error(String.format("%S already initialized!!!", App.class.getSimpleName()));
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
        //TODO implement fetching user rights and roles
        rightsAndRoles = new HashMap<>(0);
    }

    /**
     * Clear all app constants and reload them.
     */
    public void reloadConfig() {
        rightsAndRoles.clear();

        loadConfig();
    }

    /**
     *
     * @return Map with user rights and roles.
     */
    public Map<String, List<String>> getRightsAndRoles() {
        return rightsAndRoles;
    }
}
