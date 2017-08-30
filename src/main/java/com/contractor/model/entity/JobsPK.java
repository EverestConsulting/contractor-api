package com.contractor.model.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class JobsPK implements Serializable {
    private int jobId;
    private int createdByUserId;

    @Column(name = "job_id", nullable = false)
    @Id
    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    @Column(name = "created_by_user_id", nullable = false)
    @Id
    public int getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(int createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JobsPK jobsPK = (JobsPK) o;

        if (jobId != jobsPK.jobId) return false;
        if (createdByUserId != jobsPK.createdByUserId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = jobId;
        result = 31 * result + createdByUserId;
        return result;
    }
}
