package com.contractor;

import com.contractor.controller.*;
import com.contractor.db.HibernateUtil;
import com.contractor.service.FirebaseNotificationService;
import com.contractor.service.StripePaymentService;
import com.contractor.service.httpclient.HttpClient;
import com.contractor.service.httpclient.HttpClientWorker;
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
        //init application controller
        App.init();

        //initializing DB controllers
        SessionController.init();
        UserController.init();
        JobsController.init();
        CompanyController.init();
        NotificationController.init();
        PaymentController.init();

        //initializing local services
        HttpClient.init();
        HttpClientWorker.init();

        //initialize 3rd party services
        FirebaseNotificationService.init();
        StripePaymentService.init();

        LOG.info("*** contextInitialized ***");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //close http connections
        HttpClient.shutdown();
        HibernateUtil.shutdown();
        LOG.info("*** contextDestroyed ***");
    }
}
