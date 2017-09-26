package com.contractor.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PaymentController {
    private static final Logger LOG = LoggerFactory.getLogger(PaymentController.class.getSimpleName());
    private static PaymentController INSTANCE;
//    private JobDao JOB_DAO;

    private PaymentController() {
        //singleton pattern
//        JOB_DAO = App.instance().getJobDao();
    }

    public static void init() {
        if (null != INSTANCE) {
            LOG.error(String.format("%s already initialized!!!", PaymentController.class.getSimpleName()));
            return;
        }

        INSTANCE = new PaymentController();
    }

    public static PaymentController instance() {
        if (null == INSTANCE) {
            LOG.error(String.format("%s not properly initialized!!!", PaymentController.class.getSimpleName()));
        }
        return INSTANCE;
    }
}
