package com.contractor.model.dao;

import com.contractor.model.entity.JobStatus;

public class JobStatusDao extends AbstractDao<JobStatus> {
    public JobStatusDao() {
        super(JobStatus.class);
    }
}
