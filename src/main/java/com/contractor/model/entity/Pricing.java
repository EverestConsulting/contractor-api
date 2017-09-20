package com.contractor.model.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigInteger;

@Entity
public class Pricing {
    private Integer pricingId;
    private BigInteger price;
    private String priceCurrency;
    private String priceUnit;
    private Integer jobTypeId;

    @Id
    @Column(name = "pricing_id", nullable = false)
    public Integer getPricingId() {
        return pricingId;
    }

    public void setPricingId(Integer pricingId) {
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
    @Column(name = "job_type_id", nullable = false)
    public Integer getJobTypeId() {
        return jobTypeId;
    }

    public void setJobTypeId(Integer jobTypeId) {
        this.jobTypeId = jobTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pricing pricing = (Pricing) o;

        if (pricingId != null ? !pricingId.equals(pricing.pricingId) : pricing.pricingId != null) return false;
        if (price != null ? !price.equals(pricing.price) : pricing.price != null) return false;
        if (priceCurrency != null ? !priceCurrency.equals(pricing.priceCurrency) : pricing.priceCurrency != null)
            return false;
        if (priceUnit != null ? !priceUnit.equals(pricing.priceUnit) : pricing.priceUnit != null) return false;
        if (jobTypeId != null ? !jobTypeId.equals(pricing.jobTypeId) : pricing.jobTypeId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pricingId != null ? pricingId.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (priceCurrency != null ? priceCurrency.hashCode() : 0);
        result = 31 * result + (priceUnit != null ? priceUnit.hashCode() : 0);
        result = 31 * result + (jobTypeId != null ? jobTypeId.hashCode() : 0);
        return result;
    }
}
