package com.contractor.model.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "job_type", schema = "public", catalog = "contractor")
public class JobType {
    private short jobTypeId;
    private String jobTypeTitle;
    private String jobTypeDescription;
    private Collection<Jobs> jobsByJobTypeId;
    private Collection<Pricing> pricingsByJobTypeId;

    @Id
    @Column(name = "job_type_id", nullable = false)
    public short getJobTypeId() {
        return jobTypeId;
    }

    public void setJobTypeId(short jobTypeId) {
        this.jobTypeId = jobTypeId;
    }

    @Basic
    @Column(name = "job_type_title", nullable = false, length = 30)
    public String getJobTypeTitle() {
        return jobTypeTitle;
    }

    public void setJobTypeTitle(String jobTypeTitle) {
        this.jobTypeTitle = jobTypeTitle;
    }

    @Basic
    @Column(name = "job_type_description", nullable = false, length = 255)
    public String getJobTypeDescription() {
        return jobTypeDescription;
    }

    public void setJobTypeDescription(String jobTypeDescription) {
        this.jobTypeDescription = jobTypeDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JobType jobType = (JobType) o;

        if (jobTypeId != jobType.jobTypeId) return false;
        if (jobTypeTitle != null ? !jobTypeTitle.equals(jobType.jobTypeTitle) : jobType.jobTypeTitle != null)
            return false;
        if (jobTypeDescription != null ? !jobTypeDescription.equals(jobType.jobTypeDescription) : jobType.jobTypeDescription != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) jobTypeId;
        result = 31 * result + (jobTypeTitle != null ? jobTypeTitle.hashCode() : 0);
        result = 31 * result + (jobTypeDescription != null ? jobTypeDescription.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "jobTypeByJobTypeId")
    public Collection<Jobs> getJobsByJobTypeId() {
        return jobsByJobTypeId;
    }

    public void setJobsByJobTypeId(Collection<Jobs> jobsByJobTypeId) {
        this.jobsByJobTypeId = jobsByJobTypeId;
    }

    @OneToMany(mappedBy = "jobTypeByJobTypeId")
    public Collection<Pricing> getPricingsByJobTypeId() {
        return pricingsByJobTypeId;
    }

    public void setPricingsByJobTypeId(Collection<Pricing> pricingsByJobTypeId) {
        this.pricingsByJobTypeId = pricingsByJobTypeId;
    }
}
