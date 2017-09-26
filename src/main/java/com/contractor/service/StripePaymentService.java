package com.contractor.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class StripePaymentService {

    private static final Logger LOG = LoggerFactory.getLogger(StripePaymentService.class.getSimpleName());

    private static StripePaymentService INSTANCE;


    private StripePaymentService() {

    }

    public static void init() {
        if (null != INSTANCE) {
            LOG.error(String.format("%s already initialized!!!", StripePaymentService.class.getSimpleName()));
            return;
        }

        INSTANCE = new StripePaymentService();
    }

    public static StripePaymentService instance() {
        if (null == INSTANCE) {
            LOG.error(String.format("%s not properly initialized!!!", StripePaymentService.class.getSimpleName()));
        }
        return INSTANCE;
    }

}
