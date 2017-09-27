package com.contractor.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "job_category", schema = "public", catalog = "contractor")
public class JobCategory {
    private Integer jobCategoryId;
    private String jobCategoryTitle;
    private String jobCategoryDescription;

    @Id
    @Column(name = "job_category_id", nullable = false)
    public Integer getJobCategoryId() {
        return jobCategoryId;
    }

    public void setJobCategoryId(Integer jobCategoryId) {
        this.jobCategoryId = jobCategoryId;
    }

    @Basic
    @Column(name = "job_category_title", nullable = false, length = 30)
    public String getJobCategoryTitle() {
        return jobCategoryTitle;
    }

    public void setJobCategoryTitle(String jobCategoryTitle) {
        this.jobCategoryTitle = jobCategoryTitle;
    }

    @Basic
    @Column(name = "job_category_description", nullable = false, length = -1)
    public String getJobCategoryDescription() {
        return jobCategoryDescription;
    }

    public void setJobCategoryDescription(String jobCategoryDescription) {
        this.jobCategoryDescription = jobCategoryDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JobCategory that = (JobCategory) o;

        if (jobCategoryId != null ? !jobCategoryId.equals(that.jobCategoryId) : that.jobCategoryId != null)
            return false;
        if (jobCategoryTitle != null ? !jobCategoryTitle.equals(that.jobCategoryTitle) : that.jobCategoryTitle != null)
            return false;
        if (jobCategoryDescription != null ? !jobCategoryDescription.equals(that.jobCategoryDescription) : that.jobCategoryDescription != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = jobCategoryId != null ? jobCategoryId.hashCode() : 0;
        result = 31 * result + (jobCategoryTitle != null ? jobCategoryTitle.hashCode() : 0);
        result = 31 * result + (jobCategoryDescription != null ? jobCategoryDescription.hashCode() : 0);
        return result;
    }
}
