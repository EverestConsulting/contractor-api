package com.contractor.model.entity;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Collection;

@Entity
public class Pricing {
    private short pricingId;
    private BigInteger price;
    private String priceCurrency;
    private String priceUnit;
    private int pricingPlanId;
    private int jobTypeId;
    private Collection<Jobs> jobsByPricingId;
    private PricingPlan pricingPlanByPricingPlanId;
    private JobType jobTypeByJobTypeId;

    @Id
    @Column(name = "pricing_id", nullable = false)
    public short getPricingId() {
        return pricingId;
    }

    public void setPricingId(short pricingId) {
        this.pricingId = pricingId;
    }

    @Basic
    @Column(name = "price", nullable = false, precision = 0)
    public BigInteger getPrice() {
        return price;
    }

    public void setPrice(BigInteger price) {
        this.price = price;
    }

    @Basic
    @Column(name = "price_currency", nullable = false, length = 15)
    public String getPriceCurrency() {
        return priceCurrency;
    }

    public void setPriceCurrency(String priceCurrency) {
        this.priceCurrency = priceCurrency;
    }

    @Basic
    @Column(name = "price_unit", nullable = false, length = 5)
    public String getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
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
        if (pricingPlanId != pricing.pricingPlanId) return false;
        if (jobTypeId != pricing.jobTypeId) return false;
        if (price != null ? !price.equals(pricing.price) : pricing.price != null) return false;
        if (priceCurrency != null ? !priceCurrency.equals(pricing.priceCurrency) : pricing.priceCurrency != null)
            return false;
        if (priceUnit != null ? !priceUnit.equals(pricing.priceUnit) : pricing.priceUnit != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) pricingId;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (priceCurrency != null ? priceCurrency.hashCode() : 0);
        result = 31 * result + (priceUnit != null ? priceUnit.hashCode() : 0);
        result = 31 * result + pricingPlanId;
        result = 31 * result + jobTypeId;
        return result;
    }

    @OneToMany(mappedBy = "pricingByJobPricingId")
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
