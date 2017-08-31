package com.contractor.model.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "pricing_plan", schema = "public", catalog = "contractor")
public class PricingPlan {
    private short pricingPlanId;
    private String pricingPlanTitle;
    private String pricingPlanDescription;
    private Collection<Pricing> pricingsByPricingPlanId;

    @Id
    @Column(name = "pricing_plan_id", nullable = false)
    public short getPricingPlanId() {
        return pricingPlanId;
    }

    public void setPricingPlanId(short pricingPlanId) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PricingPlan that = (PricingPlan) o;

        if (pricingPlanId != that.pricingPlanId) return false;
        if (pricingPlanTitle != null ? !pricingPlanTitle.equals(that.pricingPlanTitle) : that.pricingPlanTitle != null)
            return false;
        if (pricingPlanDescription != null ? !pricingPlanDescription.equals(that.pricingPlanDescription) : that.pricingPlanDescription != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) pricingPlanId;
        result = 31 * result + (pricingPlanTitle != null ? pricingPlanTitle.hashCode() : 0);
        result = 31 * result + (pricingPlanDescription != null ? pricingPlanDescription.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "pricingPlanByPricingPlanId")
    public Collection<Pricing> getPricingsByPricingPlanId() {
        return pricingsByPricingPlanId;
    }

    public void setPricingsByPricingPlanId(Collection<Pricing> pricingsByPricingPlanId) {
        this.pricingsByPricingPlanId = pricingsByPricingPlanId;
    }
}
