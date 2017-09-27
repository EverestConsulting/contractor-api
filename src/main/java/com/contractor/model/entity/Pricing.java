package com.contractor.model.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigInteger;

@Entity
public class Pricing {
    private Integer pricingId;
    private BigInteger startingPrice;
    private String staringPriceDescription;
    private BigInteger additionalPrice;
    private String additionalPriceDescription;
    private String priceCurrency;
    private String priceUnit;
    private Integer jobCategoryId;

    @Id
    @Column(name = "pricing_id", nullable = false)
    public Integer getPricingId() {
        return pricingId;
    }

    public void setPricingId(Integer pricingId) {
        this.pricingId = pricingId;
    }

    @Basic
    @Column(name = "starting_price", nullable = false, precision = 0)
    public BigInteger getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(BigInteger startingPrice) {
        this.startingPrice = startingPrice;
    }

    @Basic
    @Column(name = "staring_price_description", nullable = false, length = -1)
    public String getStaringPriceDescription() {
        return staringPriceDescription;
    }

    public void setStaringPriceDescription(String staringPriceDescription) {
        this.staringPriceDescription = staringPriceDescription;
    }

    @Basic
    @Column(name = "additional_price", nullable = false, precision = 0)
    public BigInteger getAdditionalPrice() {
        return additionalPrice;
    }

    public void setAdditionalPrice(BigInteger additionalPrice) {
        this.additionalPrice = additionalPrice;
    }

    @Basic
    @Column(name = "additional_price_description", nullable = false, length = -1)
    public String getAdditionalPriceDescription() {
        return additionalPriceDescription;
    }

    public void setAdditionalPriceDescription(String additionalPriceDescription) {
        this.additionalPriceDescription = additionalPriceDescription;
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
    @Column(name = "price_unit", nullable = false, length = 20)
    public String getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }

    @Basic
    @Column(name = "job_category_id", nullable = false)
    public Integer getJobCategoryId() {
        return jobCategoryId;
    }

    public void setJobCategoryId(Integer jobCategoryId) {
        this.jobCategoryId = jobCategoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pricing pricing = (Pricing) o;

        if (pricingId != null ? !pricingId.equals(pricing.pricingId) : pricing.pricingId != null) return false;
        if (startingPrice != null ? !startingPrice.equals(pricing.startingPrice) : pricing.startingPrice != null)
            return false;
        if (staringPriceDescription != null ? !staringPriceDescription.equals(pricing.staringPriceDescription) : pricing.staringPriceDescription != null)
            return false;
        if (additionalPrice != null ? !additionalPrice.equals(pricing.additionalPrice) : pricing.additionalPrice != null)
            return false;
        if (additionalPriceDescription != null ? !additionalPriceDescription.equals(pricing.additionalPriceDescription) : pricing.additionalPriceDescription != null)
            return false;
        if (priceCurrency != null ? !priceCurrency.equals(pricing.priceCurrency) : pricing.priceCurrency != null)
            return false;
        if (priceUnit != null ? !priceUnit.equals(pricing.priceUnit) : pricing.priceUnit != null) return false;
        if (jobCategoryId != null ? !jobCategoryId.equals(pricing.jobCategoryId) : pricing.jobCategoryId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pricingId != null ? pricingId.hashCode() : 0;
        result = 31 * result + (startingPrice != null ? startingPrice.hashCode() : 0);
        result = 31 * result + (staringPriceDescription != null ? staringPriceDescription.hashCode() : 0);
        result = 31 * result + (additionalPrice != null ? additionalPrice.hashCode() : 0);
        result = 31 * result + (additionalPriceDescription != null ? additionalPriceDescription.hashCode() : 0);
        result = 31 * result + (priceCurrency != null ? priceCurrency.hashCode() : 0);
        result = 31 * result + (priceUnit != null ? priceUnit.hashCode() : 0);
        result = 31 * result + (jobCategoryId != null ? jobCategoryId.hashCode() : 0);
        return result;
    }
}
