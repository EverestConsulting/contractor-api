package com.contractor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by dpecanac on 29.8.2017.
 */
public class AppContextListener implements ServletContextListener {
    private static final Logger LOG = LoggerFactory.getLogger(AppContextListener.class.getSimpleName());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOG.info("*** contextInitialized ***");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LOG.info("*** contextDestroyed ***");
    }
}
