package com.contractor.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Singleton class which handles business logic for handling job operations.
 */
public class JobsController {

    private static final Logger LOG = LoggerFactory.getLogger(JobsController.class.getSimpleName());
    private static JobsController INSTANCE;

    private JobsController() {
        //singleton pattern
    }

    public static void init() {
        if (null != INSTANCE) {
            LOG.error(String.format("%s already initialized!!!", JobsController.class.getSimpleName()));
            return;
        }

        INSTANCE = new JobsController();
    }

    public static JobsController getInstance() {
        if (null == INSTANCE) {
            LOG.error(String.format("%s not properly initialized!!!", JobsController.class.getSimpleName()));
        }
        return INSTANCE;
    }

    public Object createJob() {
        return null;
    }

    public Object fetchJob() {
        return null;
    }

    public Object updateJob() {
        return null;
    }

    public Object deleteJob() {
        return null;
    }

}
