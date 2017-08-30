package com.contractor.model.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "job_type", schema = "public", catalog = "contractor")
public class JobType {
    private int jobTypeId;
    private String job;
    private Collection<Jobs> jobsByJobTypeId;
    private Collection<Pricing> pricingsByJobTypeId;

    @Id
    @Column(name = "job_type_id", nullable = false)
    public int getJobTypeId() {
        return jobTypeId;
    }

    public void setJobTypeId(int jobTypeId) {
        this.jobTypeId = jobTypeId;
    }

    @Basic
    @Column(name = "job", nullable = false, length = 30)
    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JobType jobType = (JobType) o;

        if (jobTypeId != jobType.jobTypeId) return false;
        if (job != null ? !job.equals(jobType.job) : jobType.job != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = jobTypeId;
        result = 31 * result + (job != null ? job.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "jobTypeByJobType")
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
