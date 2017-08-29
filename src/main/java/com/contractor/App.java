package com.contractor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dpecanac on 29.8.2017.
 */
public class App {
    private static final Logger LOG = LoggerFactory.getLogger(App.class.getSimpleName());
    private static App INSTANCE;

    private Map<String, List<String>> rightsAndRoles;

    private App() {
        //TODO implement fetching user rights and roles
        rightsAndRoles = new HashMap<>(0);

        LOG.info("%s initialized!!!");
    }

    static void init() {
        if (null != INSTANCE) {
            LOG.error(String.format("%S already initialized!!!", App.class.getSimpleName()));
            return;
        }

        INSTANCE = new App();
    }


    public static App getInstance() {
        if (null == INSTANCE) {
            LOG.error(String.format("%s not properly initialized!!!", App.class.getSimpleName()));
        }
        return INSTANCE;
    }

    public Map<String, List<String>> getRightsAndRoles() {
        return rightsAndRoles;
    }
}
