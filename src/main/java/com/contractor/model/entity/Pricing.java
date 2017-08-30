package com.contractor.model.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Pricing {
    private int pricingId;
    private int price;
    private int pricingPlanId;
    private int jobTypeId;
    private Collection<Jobs> jobsByPricingId;
    private PricingPlan pricingPlanByPricingPlanId;
    private JobType jobTypeByJobTypeId;

    @Id
    @Column(name = "pricing_id", nullable = false)
    public int getPricingId() {
        return pricingId;
    }

    public void setPricingId(int pricingId) {
        this.pricingId = pricingId;
    }

    @Basic
    @Column(name = "price", nullable = false)
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Basic
    @Column(name = "pricing_plan_id", nullable = false)
    public int getPricingPlanId() {
        return pricingPlanId;
    }

    public void setPricingPlanId(int pricingPlanId) {
        this.pricingPlanId = pricingPlanId;
    }

    @Basic
    @Column(name = "job_type_id", nullable = false)
    public int getJobTypeId() {
        return jobTypeId;
    }

    public void setJobTypeId(int jobTypeId) {
        this.jobTypeId = jobTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pricing pricing = (Pricing) o;

        if (pricingId != pricing.pricingId) return false;
        if (price != pricing.price) return false;
        if (pricingPlanId != pricing.pricingPlanId) return false;
        if (jobTypeId != pricing.jobTypeId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pricingId;
        result = 31 * result + price;
        result = 31 * result + pricingPlanId;
        result = 31 * result + jobTypeId;
        return result;
    }

    @OneToMany(mappedBy = "pricingByPricingId")
    public Collection<Jobs> getJobsByPricingId() {
        return jobsByPricingId;
    }

    public void setJobsByPricingId(Collection<Jobs> jobsByPricingId) {
        this.jobsByPricingId = jobsByPricingId;
    }

    @ManyToOne
    @JoinColumn(name = "pricing_plan_id", referencedColumnName = "pricing_plan_id", nullable = false)
    public PricingPlan getPricingPlanByPricingPlanId() {
        return pricingPlanByPricingPlanId;
    }

    public void setPricingPlanByPricingPlanId(PricingPlan pricingPlanByPricingPlanId) {
        this.pricingPlanByPricingPlanId = pricingPlanByPricingPlanId;
    }

    @ManyToOne
    @JoinColumn(name = "job_type_id", referencedColumnName = "job_type_id", nullable = false)
    public JobType getJobTypeByJobTypeId() {
        return jobTypeByJobTypeId;
    }

    public void setJobTypeByJobTypeId(JobType jobTypeByJobTypeId) {
        this.jobTypeByJobTypeId = jobTypeByJobTypeId;
    }
}
