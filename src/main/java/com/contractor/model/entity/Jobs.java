package com.contractor.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@IdClass(JobsPK.class)
public class Jobs {
    private int jobId;
    private Integer jobType;
    private String jobLocation;
    private int createdByUserId;
    private Integer assignedToUserId;
    private Integer pricingId;
    private Timestamp created;
    private Timestamp assigned;
    private Timestamp completed;
    private JobType jobTypeByJobType;
    private Users usersByCreatedByUserId;
    private Users usersByAssignedToUserId;
    private Pricing pricingByPricingId;

    @Id
    @Column(name = "job_id", nullable = false)
    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    @Basic
    @Column(name = "job_type", nullable = true)
    public Integer getJobType() {
        return jobType;
    }

    public void setJobType(Integer jobType) {
        this.jobType = jobType;
    }

    @Basic
    @Column(name = "job_location", nullable = false, length = 100)
    public String getJobLocation() {
        return jobLocation;
    }

    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }

    @Id
    @Column(name = "created_by_user_id", nullable = false)
    public int getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(int createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    @Basic
    @Column(name = "assigned_to_user_id", nullable = true)
    public Integer getAssignedToUserId() {
        return assignedToUserId;
    }

    public void setAssignedToUserId(Integer assignedToUserId) {
        this.assignedToUserId = assignedToUserId;
    }

    @Basic
    @Column(name = "pricing_id", nullable = true)
    public Integer getPricingId() {
        return pricingId;
    }

    public void setPricingId(Integer pricingId) {
        this.pricingId = pricingId;
    }

    @Basic
    @Column(name = "created", nullable = true)
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Basic
    @Column(name = "assigned", nullable = true)
    public Timestamp getAssigned() {
        return assigned;
    }

    public void setAssigned(Timestamp assigned) {
        this.assigned = assigned;
    }

    @Basic
    @Column(name = "completed", nullable = true)
    public Timestamp getCompleted() {
        return completed;
    }

    public void setCompleted(Timestamp completed) {
        this.completed = completed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Jobs jobs = (Jobs) o;

        if (jobId != jobs.jobId) return false;
        if (createdByUserId != jobs.createdByUserId) return false;
        if (jobType != null ? !jobType.equals(jobs.jobType) : jobs.jobType != null) return false;
        if (jobLocation != null ? !jobLocation.equals(jobs.jobLocation) : jobs.jobLocation != null) return false;
        if (assignedToUserId != null ? !assignedToUserId.equals(jobs.assignedToUserId) : jobs.assignedToUserId != null)
            return false;
        if (pricingId != null ? !pricingId.equals(jobs.pricingId) : jobs.pricingId != null) return false;
        if (created != null ? !created.equals(jobs.created) : jobs.created != null) return false;
        if (assigned != null ? !assigned.equals(jobs.assigned) : jobs.assigned != null) return false;
        if (completed != null ? !completed.equals(jobs.completed) : jobs.completed != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = jobId;
        result = 31 * result + (jobType != null ? jobType.hashCode() : 0);
        result = 31 * result + (jobLocation != null ? jobLocation.hashCode() : 0);
        result = 31 * result + createdByUserId;
        result = 31 * result + (assignedToUserId != null ? assignedToUserId.hashCode() : 0);
        result = 31 * result + (pricingId != null ? pricingId.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (assigned != null ? assigned.hashCode() : 0);
        result = 31 * result + (completed != null ? completed.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "job_type", referencedColumnName = "job_type_id")
    public JobType getJobTypeByJobType() {
        return jobTypeByJobType;
    }

    public void setJobTypeByJobType(JobType jobTypeByJobType) {
        this.jobTypeByJobType = jobTypeByJobType;
    }

    @ManyToOne
    @JoinColumn(name = "created_by_user_id", referencedColumnName = "user_id", nullable = false)
    public Users getUsersByCreatedByUserId() {
        return usersByCreatedByUserId;
    }

    public void setUsersByCreatedByUserId(Users usersByCreatedByUserId) {
        this.usersByCreatedByUserId = usersByCreatedByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "assigned_to_user_id", referencedColumnName = "user_id")
    public Users getUsersByAssignedToUserId() {
        return usersByAssignedToUserId;
    }

    public void setUsersByAssignedToUserId(Users usersByAssignedToUserId) {
        this.usersByAssignedToUserId = usersByAssignedToUserId;
    }

    @ManyToOne
    @JoinColumn(name = "pricing_id", referencedColumnName = "pricing_id")
    public Pricing getPricingByPricingId() {
        return pricingByPricingId;
    }

    public void setPricingByPricingId(Pricing pricingByPricingId) {
        this.pricingByPricingId = pricingByPricingId;
    }
}
