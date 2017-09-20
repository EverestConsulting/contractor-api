package com.contractor.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "job_status", schema = "public", catalog = "contractor")
public class JobStatus {
    private Integer jobStatusId;
    private String jobStatusName;

    @Id
    @Column(name = "job_status_id", nullable = false)
    public Integer getJobStatusId() {
        return jobStatusId;
    }

    public void setJobStatusId(Integer jobStatusId) {
        this.jobStatusId = jobStatusId;
    }

    @Basic
    @Column(name = "job_status_name", nullable = true, length = 20)
    public String getJobStatusName() {
        return jobStatusName;
    }

    public void setJobStatusName(String jobStatusName) {
        this.jobStatusName = jobStatusName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JobStatus jobStatus = (JobStatus) o;

        if (jobStatusId != null ? !jobStatusId.equals(jobStatus.jobStatusId) : jobStatus.jobStatusId != null)
            return false;
        if (jobStatusName != null ? !jobStatusName.equals(jobStatus.jobStatusName) : jobStatus.jobStatusName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = jobStatusId != null ? jobStatusId.hashCode() : 0;
        result = 31 * result + (jobStatusName != null ? jobStatusName.hashCode() : 0);
        return result;
    }
}
