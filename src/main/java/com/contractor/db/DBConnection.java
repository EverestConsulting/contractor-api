package com.contractor.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBConnection {

    private static final Logger LOG = LoggerFactory.getLogger(DBConnection.class.getSimpleName());

    private static DBConnection INSTANCE = null;
    private EntityManager entityManager;

    private DBConnection() {
        //singleton
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("");
    }


    /**
     * init singleton.
     */
    static void init() {
        if (null != INSTANCE) {
            LOG.error(String.format("%s already initialized!!!", DBConnection.class.getSimpleName()));
            return;
        }

        INSTANCE = new DBConnection();
    }


    /**
     * @return App instance
     */
    public static DBConnection getInstance() {
        if (null == INSTANCE) {
            LOG.error(String.format("%s not properly initialized!!!", DBConnection.class.getSimpleName()));
        }
        return INSTANCE;
    }

}
