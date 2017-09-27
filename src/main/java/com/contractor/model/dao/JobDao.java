package com.contractor.model.dao;

import com.contractor.model.entity.Job;

public class JobDao extends AbstractDao<Job> {
    public JobDao() {
        super(Job.class);
    }
}
