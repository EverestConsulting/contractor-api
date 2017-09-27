package com.contractor.controller;

import com.contractor.App;
import com.contractor.api.ResponseFactory;
import com.contractor.model.dao.JobDao;
import com.contractor.model.entity.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;

/**
 * Singleton class which handles business logic for handling job operations.
 */
public class JobsController {

    private static final Logger LOG = LoggerFactory.getLogger(JobsController.class.getSimpleName());
    private static JobsController INSTANCE;
    private JobDao JOB_DAO;

    private JobsController() {
        //singleton pattern
        JOB_DAO = App.instance().getJobDao();
    }

    public static void init() {
        if (null != INSTANCE) {
            LOG.error(String.format("%s already initialized!!!", JobsController.class.getSimpleName()));
            return;
        }

        INSTANCE = new JobsController();
    }

    public static JobsController instance() {
        if (null == INSTANCE) {
            LOG.error(String.format("%s not properly initialized!!!", JobsController.class.getSimpleName()));
        }
        return INSTANCE;
    }

    public Response createJob(Job job) {

        Integer id = JOB_DAO.create(job);

        if (null == id) {
            return ResponseFactory.getBadRequest400("Couldn't update user, make sure all fields are present");
        }

        job.setJobId(Long.valueOf(String.valueOf(id)));

        return ResponseFactory.getCreated201(job);
    }

    public Response fetchJob(Integer jobId) {
        Job job = JOB_DAO.get(jobId);

        if (null == job) {
            return ResponseFactory.getNotFound404();
        }


        return ResponseFactory.getSuccess200(job);
    }

    public Response updateJob(Integer jobId, Job job) {

        if (JOB_DAO.update(job)) {
            return ResponseFactory.getInternalError500();
        }
        return ResponseFactory.getSuccess200(job);
    }

    public Response deleteJob(Integer jobId) {
        Job job = JOB_DAO.get(jobId);

        if (null == job) {
            return ResponseFactory.getNotFound404();
        }

        if (JOB_DAO.delete(job)) {
            return ResponseFactory.getNoContent204();
        }

        return ResponseFactory.getInternalError500();
    }

}
