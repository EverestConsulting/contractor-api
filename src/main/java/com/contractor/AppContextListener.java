package com.contractor;

import com.contractor.controller.JobsController;
import com.contractor.controller.SessionController;
import com.contractor.controller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by dpecanac on 29.8.2017.
 */
@WebListener
public class AppContextListener implements ServletContextListener {
    private static final Logger LOG = LoggerFactory.getLogger(AppContextListener.class.getSimpleName());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        App.init();
        SessionController.init();
        UserController.init();
        JobsController.init();
        LOG.info("*** contextInitialized ***");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LOG.info("*** contextDestroyed ***");
    }
}
