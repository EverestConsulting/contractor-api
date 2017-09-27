package com.contractor.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "job_status", schema = "public", catalog = "contractor")
public class JobStatus {
    private Integer jobStatusId;
    private Long jobId;
    private Integer userId;
    private String jobStatusTitle;
    private Timestamp created;

    @Id
    @Column(name = "job_status_id", nullable = false)
    public Integer getJobStatusId() {
        return jobStatusId;
    }

    public void setJobStatusId(Integer jobStatusId) {
        this.jobStatusId = jobStatusId;
    }

    @Basic
    @Column(name = "job_id", nullable = false)
    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "job_status_title", nullable = true, length = 15)
    public String getJobStatusTitle() {
        return jobStatusTitle;
    }

    public void setJobStatusTitle(String jobStatusTitle) {
        this.jobStatusTitle = jobStatusTitle;
    }

    @Basic
    @Column(name = "created", nullable = false)
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JobStatus jobStatus = (JobStatus) o;

        if (jobStatusId != null ? !jobStatusId.equals(jobStatus.jobStatusId) : jobStatus.jobStatusId != null)
            return false;
        if (jobId != null ? !jobId.equals(jobStatus.jobId) : jobStatus.jobId != null) return false;
        if (userId != null ? !userId.equals(jobStatus.userId) : jobStatus.userId != null) return false;
        if (jobStatusTitle != null ? !jobStatusTitle.equals(jobStatus.jobStatusTitle) : jobStatus.jobStatusTitle != null)
            return false;
        if (created != null ? !created.equals(jobStatus.created) : jobStatus.created != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = jobStatusId != null ? jobStatusId.hashCode() : 0;
        result = 31 * result + (jobId != null ? jobId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (jobStatusTitle != null ? jobStatusTitle.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        return result;
    }
}
