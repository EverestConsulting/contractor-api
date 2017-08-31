package com.contractor.model.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class JobsPK implements Serializable {
    private long jobId;
    private long jobCreatedByUserId;

    @Column(name = "job_id", nullable = false)
    @Id
    public long getJobId() {
        return jobId;
    }

    public void setJobId(long jobId) {
        this.jobId = jobId;
    }

    @Column(name = "job_created_by_user_id", nullable = false)
    @Id
    public long getJobCreatedByUserId() {
        return jobCreatedByUserId;
    }

    public void setJobCreatedByUserId(long jobCreatedByUserId) {
        this.jobCreatedByUserId = jobCreatedByUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JobsPK jobsPK = (JobsPK) o;

        if (jobId != jobsPK.jobId) return false;
        if (jobCreatedByUserId != jobsPK.jobCreatedByUserId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (jobId ^ (jobId >>> 32));
        result = 31 * result + (int) (jobCreatedByUserId ^ (jobCreatedByUserId >>> 32));
        return result;
    }
}
