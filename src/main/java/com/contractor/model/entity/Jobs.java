package com.contractor.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@IdClass(JobsPK.class)
public class Jobs {
    private long jobId;
    private short jobTypeId;
    private String jobLocation;
    private long jobCreatedByUserId;
    private long jobAssignedToUserId;
    private short jobPricingId;
    private String jobNotes;
    private Timestamp jobCreated;
    private Timestamp jobAssigned;
    private Timestamp jobCompleted;
    private JobType jobTypeByJobTypeId;
    private Users usersByJobCreatedByUserId;
    private Users usersByJobAssignedToUserId;
    private Pricing pricingByJobPricingId;

    @Id
    @Column(name = "job_id", nullable = false)
    public long getJobId() {
        return jobId;
    }

    public void setJobId(long jobId) {
        this.jobId = jobId;
    }

    @Basic
    @Column(name = "job_type_id", nullable = true)
    public short getJobTypeId() {
        return jobTypeId;
    }

    public void setJobTypeId(short jobTypeId) {
        this.jobTypeId = jobTypeId;
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
    @Column(name = "job_created_by_user_id", nullable = false)
    public long getJobCreatedByUserId() {
        return jobCreatedByUserId;
    }

    public void setJobCreatedByUserId(long jobCreatedByUserId) {
        this.jobCreatedByUserId = jobCreatedByUserId;
    }

    @Basic
    @Column(name = "job_assigned_to_user_id", nullable = true)
    public long getJobAssignedToUserId() {
        return jobAssignedToUserId;
    }

    public void setJobAssignedToUserId(long jobAssignedToUserId) {
        this.jobAssignedToUserId = jobAssignedToUserId;
    }

    @Basic
    @Column(name = "job_pricing_id", nullable = true)
    public short getJobPricingId() {
        return jobPricingId;
    }

    public void setJobPricingId(short jobPricingId) {
        this.jobPricingId = jobPricingId;
    }

    @Basic
    @Column(name = "job_notes", nullable = true, length = 255)
    public String getJobNotes() {
        return jobNotes;
    }

    public void setJobNotes(String jobNotes) {
        this.jobNotes = jobNotes;
    }

    @Basic
    @Column(name = "job_created", nullable = true)
    public Timestamp getJobCreated() {
        return jobCreated;
    }

    public void setJobCreated(Timestamp jobCreated) {
        this.jobCreated = jobCreated;
    }

    @Basic
    @Column(name = "job_assigned", nullable = true)
    public Timestamp getJobAssigned() {
        return jobAssigned;
    }

    public void setJobAssigned(Timestamp jobAssigned) {
        this.jobAssigned = jobAssigned;
    }

    @Basic
    @Column(name = "job_completed", nullable = true)
    public Timestamp getJobCompleted() {
        return jobCompleted;
    }

    public void setJobCompleted(Timestamp jobCompleted) {
        this.jobCompleted = jobCompleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Jobs jobs = (Jobs) o;

        if (jobId != jobs.jobId) return false;
        if (jobTypeId != jobs.jobTypeId) return false;
        if (jobCreatedByUserId != jobs.jobCreatedByUserId) return false;
        if (jobAssignedToUserId != jobs.jobAssignedToUserId) return false;
        if (jobPricingId != jobs.jobPricingId) return false;
        if (jobLocation != null ? !jobLocation.equals(jobs.jobLocation) : jobs.jobLocation != null) return false;
        if (jobNotes != null ? !jobNotes.equals(jobs.jobNotes) : jobs.jobNotes != null) return false;
        if (jobCreated != null ? !jobCreated.equals(jobs.jobCreated) : jobs.jobCreated != null) return false;
        if (jobAssigned != null ? !jobAssigned.equals(jobs.jobAssigned) : jobs.jobAssigned != null) return false;
        if (jobCompleted != null ? !jobCompleted.equals(jobs.jobCompleted) : jobs.jobCompleted != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (jobId ^ (jobId >>> 32));
        result = 31 * result + (int) jobTypeId;
        result = 31 * result + (jobLocation != null ? jobLocation.hashCode() : 0);
        result = 31 * result + (int) (jobCreatedByUserId ^ (jobCreatedByUserId >>> 32));
        result = 31 * result + (int) (jobAssignedToUserId ^ (jobAssignedToUserId >>> 32));
        result = 31 * result + (int) jobPricingId;
        result = 31 * result + (jobNotes != null ? jobNotes.hashCode() : 0);
        result = 31 * result + (jobCreated != null ? jobCreated.hashCode() : 0);
        result = 31 * result + (jobAssigned != null ? jobAssigned.hashCode() : 0);
        result = 31 * result + (jobCompleted != null ? jobCompleted.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "job_type_id", referencedColumnName = "job_type_id")
    public JobType getJobTypeByJobTypeId() {
        return jobTypeByJobTypeId;
    }

    public void setJobTypeByJobTypeId(JobType jobTypeByJobTypeId) {
        this.jobTypeByJobTypeId = jobTypeByJobTypeId;
    }

    @ManyToOne
    @JoinColumn(name = "job_created_by_user_id", referencedColumnName = "user_id", nullable = false)
    public Users getUsersByJobCreatedByUserId() {
        return usersByJobCreatedByUserId;
    }

    public void setUsersByJobCreatedByUserId(Users usersByJobCreatedByUserId) {
        this.usersByJobCreatedByUserId = usersByJobCreatedByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "job_assigned_to_user_id", referencedColumnName = "user_id")
    public Users getUsersByJobAssignedToUserId() {
        return usersByJobAssignedToUserId;
    }

    public void setUsersByJobAssignedToUserId(Users usersByJobAssignedToUserId) {
        this.usersByJobAssignedToUserId = usersByJobAssignedToUserId;
    }

    @ManyToOne
    @JoinColumn(name = "job_pricing_id", referencedColumnName = "pricing_id")
    public Pricing getPricingByJobPricingId() {
        return pricingByJobPricingId;
    }

    public void setPricingByJobPricingId(Pricing pricingByJobPricingId) {
        this.pricingByJobPricingId = pricingByJobPricingId;
    }
}
