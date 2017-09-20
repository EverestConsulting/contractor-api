package com.contractor.model.dao;

import com.contractor.model.entity.Job;
import com.contractor.model.entity.JobType;

public class JobDao extends AbstractDao<Job> {
    public JobDao() {
        super(Job.class);
    }
}
