package com.contractor.model.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Job {
    private Long jobId;
    private Integer jobCategoryId;
    private String jobLocation;
    private Integer jobCreatedByUserId;
    private Integer jobAssignedToUserId;
    private Integer jobPricingId;
    private Integer jobPricingPlanId;
    private String jobNotes;
    private Timestamp jobCreated;
    private Timestamp jobAssigned;
    private Timestamp jobCompleted;

    @Id
    @Column(name = "job_id", nullable = false)
    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    @Basic
    @Column(name = "job_category_id", nullable = false)
    public Integer getJobCategoryId() {
        return jobCategoryId;
    }

    public void setJobCategoryId(Integer jobCategoryId) {
        this.jobCategoryId = jobCategoryId;
    }

    @Basic
    @Column(name = "job_location", nullable = false, length = 100)
    public String getJobLocation() {
        return jobLocation;
    }

    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }

    @Basic
    @Column(name = "job_created_by_user_id", nullable = false)
    public Integer getJobCreatedByUserId() {
        return jobCreatedByUserId;
    }

    public void setJobCreatedByUserId(Integer jobCreatedByUserId) {
        this.jobCreatedByUserId = jobCreatedByUserId;
    }

    @Basic
    @Column(name = "job_assigned_to_user_id", nullable = true)
    public Integer getJobAssignedToUserId() {
        return jobAssignedToUserId;
    }

    public void setJobAssignedToUserId(Integer jobAssignedToUserId) {
        this.jobAssignedToUserId = jobAssignedToUserId;
    }

    @Basic
    @Column(name = "job_pricing_id", nullable = false)
    public Integer getJobPricingId() {
        return jobPricingId;
    }

    public void setJobPricingId(Integer jobPricingId) {
        this.jobPricingId = jobPricingId;
    }

    @Basic
    @Column(name = "job_pricing_plan_id", nullable = false)
    public Integer getJobPricingPlanId() {
        return jobPricingPlanId;
    }

    public void setJobPricingPlanId(Integer jobPricingPlanId) {
        this.jobPricingPlanId = jobPricingPlanId;
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
    @Column(name = "job_created", nullable = false)
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

        Job job = (Job) o;

        if (jobId != null ? !jobId.equals(job.jobId) : job.jobId != null) return false;
        if (jobCategoryId != null ? !jobCategoryId.equals(job.jobCategoryId) : job.jobCategoryId != null) return false;
        if (jobLocation != null ? !jobLocation.equals(job.jobLocation) : job.jobLocation != null) return false;
        if (jobCreatedByUserId != null ? !jobCreatedByUserId.equals(job.jobCreatedByUserId) : job.jobCreatedByUserId != null)
            return false;
        if (jobAssignedToUserId != null ? !jobAssignedToUserId.equals(job.jobAssignedToUserId) : job.jobAssignedToUserId != null)
            return false;
        if (jobPricingId != null ? !jobPricingId.equals(job.jobPricingId) : job.jobPricingId != null) return false;
        if (jobPricingPlanId != null ? !jobPricingPlanId.equals(job.jobPricingPlanId) : job.jobPricingPlanId != null)
            return false;
        if (jobNotes != null ? !jobNotes.equals(job.jobNotes) : job.jobNotes != null) return false;
        if (jobCreated != null ? !jobCreated.equals(job.jobCreated) : job.jobCreated != null) return false;
        if (jobAssigned != null ? !jobAssigned.equals(job.jobAssigned) : job.jobAssigned != null) return false;
        if (jobCompleted != null ? !jobCompleted.equals(job.jobCompleted) : job.jobCompleted != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = jobId != null ? jobId.hashCode() : 0;
        result = 31 * result + (jobCategoryId != null ? jobCategoryId.hashCode() : 0);
        result = 31 * result + (jobLocation != null ? jobLocation.hashCode() : 0);
        result = 31 * result + (jobCreatedByUserId != null ? jobCreatedByUserId.hashCode() : 0);
        result = 31 * result + (jobAssignedToUserId != null ? jobAssignedToUserId.hashCode() : 0);
        result = 31 * result + (jobPricingId != null ? jobPricingId.hashCode() : 0);
        result = 31 * result + (jobPricingPlanId != null ? jobPricingPlanId.hashCode() : 0);
        result = 31 * result + (jobNotes != null ? jobNotes.hashCode() : 0);
        result = 31 * result + (jobCreated != null ? jobCreated.hashCode() : 0);
        result = 31 * result + (jobAssigned != null ? jobAssigned.hashCode() : 0);
        result = 31 * result + (jobCompleted != null ? jobCompleted.hashCode() : 0);
        return result;
    }
}
