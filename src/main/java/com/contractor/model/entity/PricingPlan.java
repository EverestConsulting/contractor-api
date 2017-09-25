package com.contractor.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "pricing_plan", schema = "public", catalog = "contractor")
public class PricingPlan {
    private Integer pricingPlanId;
    private String pricingPlanTitle;
    private String pricingPlanDescription;
    private Float pricingPlanCoefficient;

    @Id
    @Column(name = "pricing_plan_id", nullable = false)
    public Integer getPricingPlanId() {
        return pricingPlanId;
    }

    public void setPricingPlanId(Integer pricingPlanId) {
        this.pricingPlanId = pricingPlanId;
    }

    @Basic
    @Column(name = "pricing_plan_title", nullable = false, length = 30)
    public String getPricingPlanTitle() {
        return pricingPlanTitle;
    }

    public void setPricingPlanTitle(String pricingPlanTitle) {
        this.pricingPlanTitle = pricingPlanTitle;
    }

    @Basic
    @Column(name = "pricing_plan_description", nullable = false, length = 255)
    public String getPricingPlanDescription() {
        return pricingPlanDescription;
    }

    public void setPricingPlanDescription(String pricingPlanDescription) {
        this.pricingPlanDescription = pricingPlanDescription;
    }

    @Basic
    @Column(name = "pricing_plan_coefficient", nullable = false, precision = 0)
    public Float getPricingPlanCoefficient() {
        return pricingPlanCoefficient;
    }

    public void setPricingPlanCoefficient(Float pricingPlanCoefficient) {
        this.pricingPlanCoefficient = pricingPlanCoefficient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PricingPlan that = (PricingPlan) o;

        if (pricingPlanId != null ? !pricingPlanId.equals(that.pricingPlanId) : that.pricingPlanId != null)
            return false;
        if (pricingPlanTitle != null ? !pricingPlanTitle.equals(that.pricingPlanTitle) : that.pricingPlanTitle != null)
            return false;
        if (pricingPlanDescription != null ? !pricingPlanDescription.equals(that.pricingPlanDescription) : that.pricingPlanDescription != null)
            return false;
        if (pricingPlanCoefficient != null ? !pricingPlanCoefficient.equals(that.pricingPlanCoefficient) : that.pricingPlanCoefficient != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pricingPlanId != null ? pricingPlanId.hashCode() : 0;
        result = 31 * result + (pricingPlanTitle != null ? pricingPlanTitle.hashCode() : 0);
        result = 31 * result + (pricingPlanDescription != null ? pricingPlanDescription.hashCode() : 0);
        result = 31 * result + (pricingPlanCoefficient != null ? pricingPlanCoefficient.hashCode() : 0);
        return result;
    }
}
